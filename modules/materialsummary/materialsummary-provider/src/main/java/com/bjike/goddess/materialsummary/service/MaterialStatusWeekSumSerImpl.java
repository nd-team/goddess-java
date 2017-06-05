package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.MaterialStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialStatusWeekSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 物资状态周汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:21 ]
* @Description:	[ 物资状态周汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class MaterialStatusWeekSumSerImpl extends ServiceImpl<MaterialStatusWeekSum, MaterialStatusWeekSumDTO> implements MaterialStatusWeekSumSer { 

 }