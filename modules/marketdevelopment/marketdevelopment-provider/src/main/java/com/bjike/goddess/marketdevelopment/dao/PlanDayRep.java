package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.PlanDayDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanDay;

/**
* 日计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-29 03:55 ]
* @Description:	[ 日计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PlanDayRep extends JpaRep<PlanDay ,PlanDayDTO> { 

 }