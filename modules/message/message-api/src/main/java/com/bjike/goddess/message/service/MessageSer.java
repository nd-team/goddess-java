package com.bjike.goddess.message.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.to.MessageTO;

import java.util.List;

/**
 * 消息推送业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.621 ]
 * @Description: [ 消息推送业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MessageSer extends Ser<Message, MessageDTO> {
    /**
     * 发送消息
     *
     * @param messageTO
     */
    default void send(MessageTO messageTO) throws SerException {

    }

    /**
     * 读取消息
     *
     * @param messageId
     */
    default void read(String messageId) throws SerException {
    }

    /**
     * 读取消息列表
     *
     * @param dto
     */
    default List<MessageBO> list(MessageDTO dto) throws SerException {
        return null;
    }

    /**
     * 未读消息列表
     * @param userId
     * @param type
     * @return
     * @throws SerException
     */
    default List<MessageBO> unreadList(String userId, MsgType type) throws SerException{
        return null;
    }


    /**
     * 删除消息
     *
     * @param messageId 消息id
     */
    default void remove(String messageId) throws SerException {
    }

    /**
     * 修改消息
     *
     * @param messageTO 消息id
     */
    default void edit(MessageTO messageTO) throws SerException {
    }

}