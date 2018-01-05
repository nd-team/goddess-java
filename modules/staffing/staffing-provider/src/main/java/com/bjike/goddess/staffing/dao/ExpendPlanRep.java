package com.bjike.goddess.staffing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffing.dto.ExpendPlanDTO;
import com.bjike.goddess.staffing.entity.ExpendPlan;

/**
* 人工成本计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-31 11:47 ]
* @Description:	[ 人工成本计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ExpendPlanRep extends JpaRep<ExpendPlan ,ExpendPlanDTO> { 

 }