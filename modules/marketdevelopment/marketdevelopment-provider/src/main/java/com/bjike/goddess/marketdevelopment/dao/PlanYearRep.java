package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.PlanYearDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanYear;

/**
* 年计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-07 05:13 ]
* @Description:	[ 年计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PlanYearRep extends JpaRep<PlanYear ,PlanYearDTO> { 

 }