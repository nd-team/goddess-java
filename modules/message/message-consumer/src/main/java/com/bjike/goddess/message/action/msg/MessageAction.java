package com.bjike.goddess.message.action.msg;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.EmailAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.kafka.KafkaConsumer;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
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
@LoginAuth
@RequestMapping("message")
public class MessageAction {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private EmailAPI emailAPI;
    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        KafkaConsumer.emailAPI = emailAPI;
        KafkaConsumer.env = env;
    }

    /**
     * 发送消息
     *
     * @param messageTO 消息体
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/send")
    public Result send(@Validated(ADD.class) MessageTO messageTO, BindingResult result) throws ActException {
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
     * @param id 消息id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/{id}/read")
    public Result read(@PathVariable String id) throws ActException {
        try {
            messageAPI.read(id);
            return ActResult.initialize("read success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取消息
     *
     * @param id      用户id
     * @param msgType 消息类型
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/{id}/messages")
    public Result messages(@PathVariable String id, MsgType msgType, HttpServletRequest request) throws ActException {
        try {
            MessageDTO dto = new MessageDTO();
            dto.setUserId(id);
            dto.setMsgType(msgType);
            List<MessageBO> messageBOS = messageAPI.list(dto);
            List<MessageVO> messageVOS = BeanTransform.copyProperties(messageBOS, MessageVO.class, request);
            return ActResult.initialize(messageVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 未读消息
     *
     * @param id 用户id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/{id}/unread-messages")
    public Result unreadMessages(@PathVariable String id, MsgType msgType, HttpServletRequest request) throws ActException {
        try {
            List<MessageBO> messageBOS = messageAPI.unreadList(id, msgType);
            List<MessageVO> messageVOS = BeanTransform.copyProperties(messageBOS, MessageVO.class, request);
            return ActResult.initialize(messageVOS);
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
    public Result edit(@Validated(EDIT.class) MessageTO messageTO, BindingResult result) throws ActException {
        try {
            messageAPI.edit(messageTO);
            return new ActResult("edit is success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 消息删除
     *
     * @param id 消息id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            messageAPI.remove(id);
            return new ActResult("delete is success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
