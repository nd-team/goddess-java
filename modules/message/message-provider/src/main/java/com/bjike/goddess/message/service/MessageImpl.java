package com.bjike.goddess.message.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.bo.MessageRead;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.GroupMessage;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.entity.UserMessage;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.kafka.IKafkaProducer;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息推送业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.627 ]
 * @Description: [ 消息推送业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "messageSerCache")
@Service
public class MessageImpl extends ServiceImpl<Message, MessageDTO> implements MessageSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private IKafkaProducer kafkaProducer;
    @Autowired
    private GroupMessageSer groupMessageSer;
    @Autowired
    private UserMessageSer userMessageSer;

    @Transactional
    @Override
    public void send(MessageTO messageTO) throws SerException {
        if (StringUtils.isBlank(messageTO.getCreateTime())) {
            messageTO.setCreateTime(DateUtil.dateToString(LocalDateTime.now()));
        }
        if (StringUtils.isBlank(messageTO.getSenderId())) {
            try {//如果经过action调用,必须登录用户
                UserBO userBO = userAPI.currentUser();
                messageTO.setSenderId(userBO.getId());
                messageTO.setSenderName(userBO.getUsername());
            } catch (SerException e) { //定时器自动调用没有登录用户
                messageTO.setSenderId("sys");
                messageTO.setSenderName("admin");
            }

        }
        messageTO.setMsgType(null != messageTO.getMsgType() ? messageTO.getMsgType() : MsgType.SYS);
        messageTO.setSendType(null != messageTO.getSendType() ? messageTO.getSendType() : SendType.MSG);
        messageTO.setRangeType(null != messageTO.getRangeType() ? messageTO.getRangeType() : RangeType.SPECIFIED);

        Message message = BeanTransform.copyProperties(messageTO, Message.class, true);
        super.save(message);
        saveMessage(messageTO, message);
        messageTO.setId(message.getId());
        String[] receivers = null;
        String[] receiversEmail = null;
        boolean isEmail = true;
        for (String email : messageTO.getReceivers()) {
            if (!Validator.isEmail(email)) {
                isEmail = false;
            }
        }
        if (!isEmail) {
            List<UserBO> userBOS = getReceivers(messageTO);
            if (null != userBOS && userBOS.size() > 0) {
                receivers = new String[userBOS.size()];
                receiversEmail = new String[userBOS.size()];
                for (int i = 0; i < userBOS.size(); i++) {
                    receivers[i] = userBOS.get(i).getId();
                    receiversEmail[i] = userBOS.get(i).getEmail();
                }
            }
        } else {//receivers 直接为邮箱方式
            receiversEmail = messageTO.getReceivers();
        }

        SendType sendType = messageTO.getSendType();

        switch (sendType) {
            case EMAIL:
                messageTO.setReceivers(receiversEmail);
                kafkaProducer.produce(messageTO);
                break;
            case MSG:
                saveMsgToRedis(messageTO, message.getId(), receivers); //保存到redis
            case ALL:
                saveMsgToRedis(messageTO, message.getId(), receivers);//保存到redis
                messageTO.setReceivers(receiversEmail);
                kafkaProducer.produce(messageTO);
                break;
        }
    }

    @Override
    public void read(String messageId) throws SerException {
        String userId = userAPI.currentUser().getId();
        redisClient.removeToList(userId + "_message", messageId); //从用户消息列表移除
    }

    @Override
    public List<MessageBO> list(MessageDTO dto) throws SerException {
        UserDetailBO detailBO = userDetailAPI.findByUserId(dto.getUserId());
        String groupId = "-1";
        if (null != detailBO) {
            groupId = detailBO.getGroupId();
        }
        Integer start = (dto.getPage() - 1 < 0 ? 0 : dto.getPage() - 1) * dto.getLimit();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,createTime,modifyTime,title,content ,senderId,senderName from (select * from message where rangeType = 0 ");//公共消息
        sb.append(" union ");
        sb.append(" select a.*  from message a,message_group_message b where a.id=b.message_id and ");//组消息
        sb.append(" b.group_id='%s' and rangeType = 1 ");
        sb.append(" union "); //个人消息
        sb.append(" select a.*  from message a, message_user_message b where a.id = b.message_id and b.user_id='%s' and rangeType=2 ) as a ");
        if (null != dto.getMsgType()) {
            sb.append(" where msgType=" + dto.getMsgType().getCode());
        }
        sb.append(" order by createTime desc limit "+start+","+dto.getLimit());
        String sql = sb.toString();
        sql = String.format(sql, groupId, dto.getUserId());
        String[] fields = new String[]{"id", "createTime", "modifyTime", "title", "content", "senderId", "senderName"};
        List<Message> messages = super.findBySql(sql, Message.class, fields); //公共的
        return BeanTransform.copyProperties(messages, MessageBO.class);
    }

    @Override
    public List<MessageBO> unreadList(String userId, MsgType type) throws SerException {
        List<String> messageIds = redisClient.getList(userId + "_message");
        if (messageIds.size() > 0) {
            MessageDTO dto = new MessageDTO();
            dto.getConditions().add(Restrict.in("id", messageIds.toArray()));
            if (null != type) {
                dto.getConditions().add(Restrict.eq("msgType", type.getCode()));
            }
            return BeanTransform.copyProperties(super.findByCis(dto), MessageBO.class);
        } else {
            return null;
        }
    }

    @Override
    public void remove(String messageId) throws SerException {
        redisClient.removeMap("message", messageId); //从缓存消息列表删除
        super.remove(messageId); //数据库删除
    }

    @Override
    public void edit(MessageTO messageTO) throws SerException {
        Message message = super.findById(messageTO.getId());
        if (null != message) {
            BeanTransform.copyProperties(messageTO, message);
            message.setModifyTime(LocalDateTime.now());
            super.update(message);
            MessageRead messageRead = BeanTransform.copyProperties(message, MessageRead.class);
            String json_messageRead = JSON.toJSONString(messageRead);
            redisClient.appendToMap("message", message.getId(), json_messageRead);
        } else {
            throw new SerException("not exist data by id");
        }

    }


    /**
     * 保存组消息及个人消息
     *
     * @param to
     * @throws SerException
     */
    private void saveMessage(MessageTO to, Message m) throws SerException {
        RangeType rangeType = to.getRangeType();
        Message message = super.findById(m.getId());
        switch (rangeType) {
            case GROUP:
                List<GroupMessage> groupMessages = new ArrayList<>();
                for (String group : to.getGroups()) {
                    GroupMessage groupMessage = new GroupMessage();
                    groupMessage.setGroupId(group);
                    groupMessage.setMessage(message);
                    groupMessages.add(groupMessage);
                }
                groupMessageSer.save(groupMessages);

                break;
            case SPECIFIED:
                List<UserMessage> userMessages = new ArrayList<>();
                for (String user : to.getReceivers()) {
                    UserMessage userMessage = new UserMessage();
                    userMessage.setMessage(message);
                    userMessage.setUserId(user);
                    userMessages.add(userMessage);
                }
                userMessageSer.save(userMessages);
                break;
            default:
                break;
        }

    }

    private List<UserBO> getReceivers(MessageTO messageTO) throws SerException {
        List<UserBO> userBOS = null;
        RangeType rangeType = messageTO.getRangeType();
        UserDTO dto = new UserDTO();
        switch (rangeType) {
            case GROUP:
                if (null != messageTO.getGroups()) {
                    userBOS = userAPI.findByGroup(messageTO.getGroups());
                } else {
                    throw new SerException("消息范围为组消息时,组id不能为空!");
                }

                break;
            case SPECIFIED:
                if (null != messageTO.getReceivers()) {
                    dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
                    dto.getConditions().add(Restrict.in("id", messageTO.getReceivers()));
                    userBOS = userAPI.findByCis(dto);
                } else {
                    throw new SerException("消息范围为个人/多人消息时,接收人id不能为空!");
                }
                break;
            case PUB:
                dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
                userBOS = userAPI.findByCis(dto);
                break;
            default:
                break;
        }

        return userBOS;
    }

    private void saveMsgToRedis(MessageTO messageTO, String message_id, String[] receivers) throws SerException {

        MessageRead messageRead = BeanTransform.copyProperties(messageTO, MessageRead.class);
        String json_messageRead = JSON.toJSONString(messageRead);

        if (null != receivers) {
            for (int i = 0; i < receivers.length; i++) {
                redisClient.appendToList(receivers[i] + "_message", 30 * 24 * 60 * 60, message_id); //未读消息保存一个月
            }
            //保存系统所发送的所有消息，以供查询
            redisClient.appendToMap("message", message_id, json_messageRead, 7 * 24 * 60 * 60);//系统所发出的消息缓存7天
        }


    }

}