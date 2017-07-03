package com.bjike.goddess.businessinteraction.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.entity.Demand;

/**
* 互动平台需求描述持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-28 03:21 ]
* @Description:	[ 互动平台需求描述持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DemandRep extends JpaRep<Demand ,DemandDTO> { 

 }