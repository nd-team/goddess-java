package com.bjike.goddess.staffwelfare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffwelfare.dto.WishesStatementDTO;
import com.bjike.goddess.staffwelfare.entity.WishesStatement;

/**
* 祝福语持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-06 03:21 ]
* @Description:	[ 祝福语持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WishesStatementRep extends JpaRep<WishesStatement ,WishesStatementDTO> { 

 }