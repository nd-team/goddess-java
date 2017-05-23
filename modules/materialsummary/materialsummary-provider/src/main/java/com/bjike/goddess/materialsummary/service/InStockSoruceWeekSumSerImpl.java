package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.InStockSoruceWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceWeekSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 入库来源周汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:05 ]
* @Description:	[ 入库来源周汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class InStockSoruceWeekSumSerImpl extends ServiceImpl<InStockSoruceWeekSum, InStockSoruceWeekSumDTO> implements InStockSoruceWeekSumSer { 

 }