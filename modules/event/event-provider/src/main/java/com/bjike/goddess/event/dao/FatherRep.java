package com.bjike.goddess.event.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.entity.Father;

/**
* 事件父表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-10 11:27 ]
* @Description:	[ 事件父表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FatherRep extends JpaRep<Father ,FatherDTO> { 

 }