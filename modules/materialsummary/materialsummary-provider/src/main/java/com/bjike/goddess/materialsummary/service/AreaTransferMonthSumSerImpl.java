package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.AreaTransferMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferMonthSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 地区调动月汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:48 ]
* @Description:	[ 地区调动月汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class AreaTransferMonthSumSerImpl extends ServiceImpl<AreaTransferMonthSum, AreaTransferMonthSumDTO> implements AreaTransferMonthSumSer { 

 }