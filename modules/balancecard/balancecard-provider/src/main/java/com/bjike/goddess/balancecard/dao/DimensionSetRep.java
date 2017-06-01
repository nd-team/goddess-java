package com.bjike.goddess.balancecard.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.balancecard.dto.DimensionSetDTO;
import com.bjike.goddess.balancecard.entity.DimensionSet;

/**
* 维度设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-19 09:01 ]
* @Description:	[ 维度设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DimensionSetRep extends JpaRep<DimensionSet ,DimensionSetDTO> { 

 }