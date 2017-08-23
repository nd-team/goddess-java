package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.YearPlan;

/**
* 年计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-22 05:57 ]
* @Description:	[ 年计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface YearPlanRep extends JpaRep<YearPlan ,YearPlanDTO> { 

 }