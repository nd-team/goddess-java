package com.bjike.goddess.courier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.courier.dto.CourierDTO;
import com.bjike.goddess.courier.entity.Courier;

/**
* 快递收发持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-28 10:25 ]
* @Description:	[ 快递收发持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CourierRep extends JpaRep<Courier ,CourierDTO> { 

 }