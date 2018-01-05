package com.bjike.goddess.balancecard.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;

/**
* 岗位指标设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-19 09:38 ]
* @Description:	[ 岗位指标设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PositionIndexSetRep extends JpaRep<PositionIndexSet ,PositionIndexSetDTO> { 

 }