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
        if(null != dto.getMsgType()){
            dto.getConditions().add(Restrict.eq("msgType",dto.getMsgType().getCode()));
        }
        dto.getConditions().add(Restrict.eq("rangeType",RangeType.PUB.getCode()));
        dto.getSorts().add("createTime");
        List<Message> messages = super.findByCis(dto); //公共的
        return null;
    }
}