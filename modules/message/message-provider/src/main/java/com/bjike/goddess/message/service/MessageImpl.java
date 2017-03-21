package com.bjike.goddess.message.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.bo.MessageRead;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private RedisClient redisClient;

    @Override
    public void send(MessageTO messageTO) throws SerException {
        if (StringUtils.isBlank(messageTO.getCreateTime())) {
            messageTO.setCreateTime(DateUtil.dateToString(LocalDateTime.now()));
        }
        if (StringUtils.isBlank(messageTO.getSenderId())) {
            UserBO userBO = userAPI.currentUser();
            messageTO.setSenderId(userBO.getId());
            messageTO.setSenderName(userBO.getUsername());
        }
        //   KafkaProducer.produce(messageTO);
        Message message = BeanTransform.copyProperties(messageTO, Message.class, true);
        super.save(message);
        saveMsgToRedis(messageTO); //保存到redis
    }

    private void saveMsgToRedis(MessageTO messageTO) throws SerException {
        List<UserBO> userBOS = null;
        String[] receivers = null;
        MessageRead messageRead = BeanTransform.copyProperties(messageTO, MessageRead.class);
        String message_id = messageTO.getId();
        String json_messageRead = JSON.toJSONString(messageRead);
        RangeType rangeType = messageTO.getRangeType();
        UserDTO dto = new UserDTO();
        switch (rangeType) {
            case GROUP:
                receivers = new String[userBOS.size()];
                break;
            case SPECIFIED:
                userBOS = userAPI.findByGroup(messageTO.getGroups());
                break;
            case PUB:
                dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
                userBOS = userAPI.findByCis(dto);
            default:
                break;
        }
        if (null != userBOS && userBOS.size() > 0) {
            receivers = new String[userBOS.size()];
            for (int i = 0; i < userBOS.size(); i++) {
                receivers[i] = userBOS.get(i).getId();
            }
        }
        if (null != receivers) {
            for (int i = 0; i < receivers.length; i++) {
                redisClient.appendToList(receivers[i] + "_message", message_id);
            }
            //保存系统所发送的所有消息，以供查询
            redisClient.appendToMap("message", message_id, json_messageRead);
        }

    }


    @Override
    public void read(String messageId) throws SerException {
        String userId = userAPI.currentUser().getId();
        redisClient.removeToList(userId + "_message", messageId);
    }

    @Override
    public List<MessageBO> list(MessageDTO dto) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from (select * from message where rangeType = 0 ");//公共消息
        sb.append(" union ");
        sb.append(" select a.*  from message a,message_group_message b, user_detail c where a.id=b.message_id and ");//组消息
        sb.append(" b.group_id=c.group_id and c.user_id = '%s' and rangeType = 1 ");
        sb.append(" union "); //个人消息
        sb.append(" select a.*  from message a, message_user_message b where a.id = b.message_id and b.user_id='%s' and rangeType=2 ) as a ");
        if (null != dto.getMsgType()) {
            sb.append(" where msgType=" + dto.getMsgType().getCode());
        }
        sb.append(" order by createTime desc ");
        String sql = String.format(sb.toString());
        sql = String.format(sql, dto.getUserId(), dto.getUserId());
        String[] fields = new String[]{"id", "createTime", "modifyTime", "title", "content", "msgType", "sendType", "sendId", "sendName"};
        List<Message> messages = super.findBySql(sql, MessageBO.class, fields); //公共的
        messages = messages.stream().skip((dto.getPage() - 1 < 0 ? 0 : dto.getPage() - 1) * dto.getLimit()).
                limit(dto.getLimit()).collect(Collectors.toList());
        return null;
    }

}