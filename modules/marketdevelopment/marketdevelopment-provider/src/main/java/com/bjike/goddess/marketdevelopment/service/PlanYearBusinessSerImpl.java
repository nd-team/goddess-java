package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.PlanYearBusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanYearBusiness;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 年计划(业务方向)业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-08 03:25 ]
* @Description:	[ 年计划(业务方向)业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class PlanYearBusinessSerImpl extends ServiceImpl<PlanYearBusiness, PlanYearBusinessDTO> implements PlanYearBusinessSer { 

 }