package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.WarrantyStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateYearSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 保修状态年汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:16 ]
* @Description:	[ 保修状态年汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class WarrantyStateYearSumSerImpl extends ServiceImpl<WarrantyStateYearSum, WarrantyStateYearSumDTO> implements WarrantyStateYearSumSer { 

 }