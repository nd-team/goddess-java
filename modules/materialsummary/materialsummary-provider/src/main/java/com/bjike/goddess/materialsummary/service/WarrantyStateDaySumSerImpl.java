package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.WarrantyStateDaySumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 保修状态日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:05 ]
* @Description:	[ 保修状态日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class WarrantyStateDaySumSerImpl extends ServiceImpl<WarrantyStateDaySum, WarrantyStateDaySumDTO> implements WarrantyStateDaySumSer { 

 }