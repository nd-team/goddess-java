package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveWeekSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 地区部门领用周汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:27 ]
* @Description:	[ 地区部门领用周汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class AreaDepartReceiveWeekSumSerImpl extends ServiceImpl<AreaDepartReceiveWeekSum, AreaDepartReceiveWeekSumDTO> implements AreaDepartReceiveWeekSumSer { 

 }