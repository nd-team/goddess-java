package com.bjike.goddess.message.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.to.MessageTO;

/**
 * 消息推送持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.626 ]
 * @Description: [ 消息推送持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IMessage extends JpaRep<Message, MessageDTO> {

}