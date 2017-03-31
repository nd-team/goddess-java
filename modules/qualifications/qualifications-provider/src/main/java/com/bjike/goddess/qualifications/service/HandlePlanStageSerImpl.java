package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.HandlePlanStageDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanStage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质办理计划阶段划分业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:55 ]
* @Description:	[ 资质办理计划阶段划分业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class HandlePlanStageSerImpl extends ServiceImpl<HandlePlanStage, HandlePlanStageDTO> implements HandlePlanStageSer { 

 }