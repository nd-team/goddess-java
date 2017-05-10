package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.entity.Buy;

/**
* 购买社保人员持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-21 02:45 ]
* @Description:	[ 购买社保人员持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BuyRep extends JpaRep<Buy ,BuyDTO> { 

 }