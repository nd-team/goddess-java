package com.bjike.goddess.message.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.message.dto.GroupMessageDTO;
import com.bjike.goddess.message.entity.GroupMessage;

/**
 * 组消息持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:07:50.374 ]
 * @Description: [ 组消息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface GroupMessageRep extends JpaRep<GroupMessage, GroupMessageDTO> {

}