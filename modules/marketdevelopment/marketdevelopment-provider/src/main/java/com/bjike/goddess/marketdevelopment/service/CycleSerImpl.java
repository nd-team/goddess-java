package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.CycleDTO;
import com.bjike.goddess.marketdevelopment.entity.Cycle;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 周期业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 03:05 ]
* @Description:	[ 周期业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class CycleSerImpl extends ServiceImpl<Cycle, CycleDTO> implements CycleSer { 

 }