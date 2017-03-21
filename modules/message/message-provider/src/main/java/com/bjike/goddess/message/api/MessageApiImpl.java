package com.bjike.goddess.message.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.service.MessageSer;
import com.bjike.goddess.message.to.MessageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 消息推送业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.625 ]
 * @Description: [ 消息推送业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("messageApiImpl")
public class MessageApiImpl implements MessageAPI {

    @Autowired
    private MessageSer messageSer;
    @Override
    public void send(@Validated MessageTO messageTO) throws SerException {
        messageSer.send(messageTO);
    }

    @Override
    public void  read(String messageId) throws SerException{
        messageSer.read(messageId);
    }

    @Override
    public List<MessageBO> list(MessageDTO dto) throws SerException {
       return messageSer.list(dto);
    }
}