package com.bjike.goddess.headcount.dao;

import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.headcount.dto.PlanCostsDTO;
import com.bjike.goddess.headcount.entity.PlanCosts;

import java.util.List;

/**
* 计划人工信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T16:48:23.651 ]
* @Description:	[ 计划人工成本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PlanCostsRep extends JpaRep<PlanCosts ,PlanCostsDTO> {

   List<PlanCosts> findByModifyTime(String time)throws RepException;

 }