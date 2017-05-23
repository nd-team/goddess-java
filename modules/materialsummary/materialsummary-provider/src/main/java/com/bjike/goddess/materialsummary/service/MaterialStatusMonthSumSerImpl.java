package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.MaterialStatusMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusMonthSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 物资状态月汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:21 ]
* @Description:	[ 物资状态月汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class MaterialStatusMonthSumSerImpl extends ServiceImpl<MaterialStatusMonthSum, MaterialStatusMonthSumDTO> implements MaterialStatusMonthSumSer { 

 }