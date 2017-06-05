package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaDepartReceiveDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 地区部门领用日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:29 ]
* @Description:	[ 地区部门领用日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class AreaDepartReceiveDaySumSerImpl extends ServiceImpl<AreaDepartReceiveDaySum, AreaDepartReceiveDaySumDTO> implements AreaDepartReceiveDaySumSer { 

 }