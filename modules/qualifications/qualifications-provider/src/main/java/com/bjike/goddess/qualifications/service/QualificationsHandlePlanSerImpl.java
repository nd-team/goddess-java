package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.QualificationsHandlePlanDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandlePlan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质办理计划业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:46 ]
* @Description:	[ 资质办理计划业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class QualificationsHandlePlanSerImpl extends ServiceImpl<QualificationsHandlePlan, QualificationsHandlePlanDTO> implements QualificationsHandlePlanSer { 

 }