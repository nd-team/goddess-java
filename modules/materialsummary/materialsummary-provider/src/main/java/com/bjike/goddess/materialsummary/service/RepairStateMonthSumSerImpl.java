package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.RepairStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateMonthSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 维修状态月汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 01:49 ]
* @Description:	[ 维修状态月汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class RepairStateMonthSumSerImpl extends ServiceImpl<RepairStateMonthSum, RepairStateMonthSumDTO> implements RepairStateMonthSumSer { 

 }