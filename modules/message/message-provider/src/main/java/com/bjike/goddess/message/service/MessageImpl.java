package com.bjike.goddess.message.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.bo.MessageRead;
import com.bjike.goddess.message.config.KafkaProducer;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
        switch (messageTO.getRangeType()) {
            case SPECIFIED:
                handleSpecifiedMsg(messageTO);
                break;
            case GROUP:
                handleSpecifiedMsg(messageTO);
                break;
            case PUB:
                handleSpecifiedMsg(messageTO);
                break;
            default: {
                handlePubMsg(messageTO);
            }
            break;
        }

        KafkaProducer.produce(messageTO);
    }

    private void handleGroupMsg(MessageTO messageTO) throws SerException{
        List<UserBO> userBOS = userAPI.findByGroup(messageTO.getGroups());
        List<String> userIds = new ArrayList<>(userBOS.size());
        userBOS.stream().forEach(userBO->{
            userIds.add(userBO.getId());
        });
        MessageRead messageRead = new MessageRead();
        messageRead.setUserId(userIds);
        messageRead.setMessageId(messageTO.getId());
        redisClient.saveList("group_message", Arrays.asList(JSON.toJSONString(messageRead)));
    }

    private void handleSpecifiedMsg(MessageTO messageTO)throws SerException {

    }

    private void handlePubMsg(MessageTO messageTO)throws SerException {

    }

    @Override
    public void read(String userId) throws SerException {
//       KafkaConsumer.consume(userId);
    }
}