package com.bjike.goddess.message.action.msg;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.entity.GET;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.to.MessageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息功能操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-15 14:26]
 * @Description: [消息推送]
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
     *
     * @param messageTO 消息体
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/send")
    public Result send(@Validated(ADD.class) MessageTO messageTO) throws ActException {
        try {
            messageAPI.send(messageTO);
            return new ActResult("send message success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取消息
     *
     * @param messageId 消息id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/read/{messageId}")
    public Result read(@PathVariable String messageId) throws ActException {
        try {
            messageAPI.read(messageId);
            return ActResult.initialize("read success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取消息
     *
     * @param dto 组消息查询对象
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(GET.class) MessageDTO dto) throws ActException {
        try {
            List<MessageBO> messageBOS = messageAPI.list(dto);
            return ActResult.initialize(messageBOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 消息修改
     *
     * @param messageTO 消息体
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result list(@Validated(EDIT.class) MessageTO messageTO) throws ActException {
        return new ActResult("");
    }

}
