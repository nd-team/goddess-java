package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.HandlePlanImplementDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanImplement;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质办理计划阶段实施工作记录业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 05:00 ]
* @Description:	[ 资质办理计划阶段实施工作记录业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class HandlePlanImplementSerImpl extends ServiceImpl<HandlePlanImplement, HandlePlanImplementDTO> implements HandlePlanImplementSer { 

 }