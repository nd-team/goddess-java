package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.WeekCycleDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekCycle;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 周计划的周期业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:37 ]
* @Description:	[ 周计划的周期业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class WeekCycleSerImpl extends ServiceImpl<WeekCycle, WeekCycleDTO> implements WeekCycleSer { 

 }