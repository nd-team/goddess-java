package com.bjike.goddess.event.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.entity.Event;

/**
* 事件持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-09 03:58 ]
* @Description:	[ 事件持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EventRep extends JpaRep<Event ,EventDTO> { 

 }