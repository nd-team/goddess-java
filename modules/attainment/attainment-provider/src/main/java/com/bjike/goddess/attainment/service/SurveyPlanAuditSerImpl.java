package com.bjike.goddess.attainment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attainment.dto.SurveyPlanAuditDTO;
import com.bjike.goddess.attainment.entity.SurveyPlanAudit;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调研计划审核记录业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 10:47 ]
* @Description:	[ 调研计划审核记录业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attainmentSerCache")
@Service
public class SurveyPlanAuditSerImpl extends ServiceImpl<SurveyPlanAudit, SurveyPlanAuditDTO> implements SurveyPlanAuditSer { 

 }