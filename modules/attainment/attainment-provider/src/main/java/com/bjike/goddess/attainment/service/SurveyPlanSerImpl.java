package com.bjike.goddess.attainment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调研计划业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-06 10:41 ]
* @Description:	[ 调研计划业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attainmentSerCache")
@Service
public class SurveyPlanSerImpl extends ServiceImpl<SurveyPlan, SurveyPlanDTO> implements SurveyPlanSer { 

 }