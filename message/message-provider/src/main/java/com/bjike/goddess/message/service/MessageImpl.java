package com.bjike.goddess.message.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.config.KafkaConsumer;
import com.bjike.goddess.message.config.KafkaProducer;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.to.MessageTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
    @Override
    public void send(MessageTO messageTO) throws SerException {
        KafkaProducer.produce(messageTO);
    }

    @Override
    public void read(String userId) throws SerException {
//       KafkaConsumer.consume(userId);
    }
}