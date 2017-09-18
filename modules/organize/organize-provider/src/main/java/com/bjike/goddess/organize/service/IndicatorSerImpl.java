package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.dto.IndicatorDTO;
import com.bjike.goddess.organize.entity.Indicator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 模块指标表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-12 02:36 ]
* @Description:	[ 模块指标表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="organizeSerCache")
@Service
public class IndicatorSerImpl extends ServiceImpl<Indicator, IndicatorDTO> implements IndicatorSer { 

 }