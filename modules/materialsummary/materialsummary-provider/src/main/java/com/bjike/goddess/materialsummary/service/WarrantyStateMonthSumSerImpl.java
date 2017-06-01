package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.WarrantyStateMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateMonthSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 保修状态月汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:15 ]
* @Description:	[ 保修状态月汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class WarrantyStateMonthSumSerImpl extends ServiceImpl<WarrantyStateMonthSum, WarrantyStateMonthSumDTO> implements WarrantyStateMonthSumSer { 

 }