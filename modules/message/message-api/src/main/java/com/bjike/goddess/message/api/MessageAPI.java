package com.bjike.goddess.message.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.bo.MessageBO;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.to.MessageTO;

import java.util.List;

/**
 * 消息推送业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-14 10:21]
 * @Description: [消息推送业务接口]
 * @Version: [v1.0.0]
 * @Copy: [com.bjike]
 */
public interface MessageAPI {

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
     * @param userId 用户id
     * @param type 消息类型
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
     * @param messageTO 消息体
     */
    default void edit(MessageTO messageTO) throws SerException {
    }
}