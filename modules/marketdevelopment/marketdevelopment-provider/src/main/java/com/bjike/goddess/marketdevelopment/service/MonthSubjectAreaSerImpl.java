package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectAreaDTO;
import com.bjike.goddess.marketdevelopment.entity.MonthSubjectArea;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 月计划省市方向(市)业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-07 10:46 ]
* @Description:	[ 月计划省市方向(市)业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class MonthSubjectAreaSerImpl extends ServiceImpl<MonthSubjectArea, MonthSubjectAreaDTO> implements MonthSubjectAreaSer { 

 }