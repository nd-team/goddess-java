package com.bjike.goddess.message.action.msg;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.to.MessageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-15 14:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("message")
public class MessageAction {
    @Autowired
    private MessageAPI messageAPI;

    /**
     * 发送消息
     * @param messageTO 消息体
     * @throws ActException
     */
    @PostMapping("v1/send")
    public Result send(MessageTO messageTO) throws ActException {
        try {
            messageAPI.send(messageTO);
            return new ActResult("send message success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取消息
     * @param messageId 消息id
     * @throws ActException
     */
    @GetMapping("v1/read/{userId}")
    public Result read(@PathVariable String messageId) throws ActException {
        try {
            messageAPI.read(messageId);
            return ActResult.initialize("");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取消息
     * @throws ActException
     */
    @GetMapping("v1/maps")
    public Result read( MessageDTO dto) throws ActException {
        try {
            List<MessageBO> messageBOS = messageAPI.list(dto);
            return ActResult.initialize("");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
