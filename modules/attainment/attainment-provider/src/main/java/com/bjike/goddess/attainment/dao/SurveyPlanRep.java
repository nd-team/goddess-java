package com.bjike.goddess.attainment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.entity.SurveyPlan;

/**
* 调研计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 10:41 ]
* @Description:	[ 调研计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SurveyPlanRep extends JpaRep<SurveyPlan ,SurveyPlanDTO> { 

 }