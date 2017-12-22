package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.PlanYearDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanYear;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 年计划业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-07 05:13 ]
* @Description:	[ 年计划业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class PlanYearSerImpl extends ServiceImpl<PlanYear, PlanYearDTO> implements PlanYearSer { 

 }