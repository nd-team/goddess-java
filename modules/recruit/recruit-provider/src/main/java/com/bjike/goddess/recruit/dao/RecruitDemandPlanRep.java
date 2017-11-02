package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.RecruitDemandPlanDTO;
import com.bjike.goddess.recruit.entity.RecruitDemandPlan;

/**
* 招聘需求与计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-10-30 03:52 ]
* @Description:	[ 招聘需求与计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RecruitDemandPlanRep extends JpaRep<RecruitDemandPlan ,RecruitDemandPlanDTO> { 

 }