package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.WarrantyStateWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateWeekSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 保修状态周汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:50 ]
* @Description:	[ 保修状态周汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class WarrantyStateWeekSumSerImpl extends ServiceImpl<WarrantyStateWeekSum, WarrantyStateWeekSumDTO> implements WarrantyStateWeekSumSer { 

 }