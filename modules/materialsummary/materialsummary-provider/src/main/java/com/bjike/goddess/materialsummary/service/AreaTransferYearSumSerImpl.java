package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.AreaTransferYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaTransferYearSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 地区调动年汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:47 ]
* @Description:	[ 地区调动年汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class AreaTransferYearSumSerImpl extends ServiceImpl<AreaTransferYearSum, AreaTransferYearSumDTO> implements AreaTransferYearSumSer { 

 }