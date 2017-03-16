package com.bjike.goddess.message.action.msg;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param userId 用户id
     * @throws ActException
     */
    @GetMapping("v1/read/{userId}")
    public Result send(@PathVariable String userId) throws ActException {
        try {
            messageAPI.read(userId);
            return ActResult.initialize("");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
