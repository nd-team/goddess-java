package com.bjike.goddess.message.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.bo.MessageBO;
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
public interface MessageAPI {

    /**
     * 发送消息
     *
     * @param messageTO
     */
    default void send(MessageTO messageTO) throws SerException{

    }

    /**
     * 读取消息
     *
     * @param userId
     */
    default void read(String userId)throws SerException {
    }
}