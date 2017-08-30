package com.bjike.goddess.attainment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attainment.dto.SurveyPlanAuditDTO;
import com.bjike.goddess.attainment.entity.SurveyPlanAudit;

/**
* 调研计划审核记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 10:47 ]
* @Description:	[ 调研计划审核记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SurveyPlanAuditRep extends JpaRep<SurveyPlanAudit ,SurveyPlanAuditDTO> { 

 }