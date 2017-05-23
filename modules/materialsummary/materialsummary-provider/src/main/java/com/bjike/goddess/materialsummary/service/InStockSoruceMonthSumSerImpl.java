package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.InStockSoruceMonthSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceMonthSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 入库来源月汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:06 ]
* @Description:	[ 入库来源月汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class InStockSoruceMonthSumSerImpl extends ServiceImpl<InStockSoruceMonthSum, InStockSoruceMonthSumDTO> implements InStockSoruceMonthSumSer { 

 }