package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.InStockSoruceDaySumDTO;
import com.bjike.goddess.materialsummary.entity.InStockSoruceDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 入库来源日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:04 ]
* @Description:	[ 入库来源日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class InStockSoruceDaySumSerImpl extends ServiceImpl<InStockSoruceDaySum, InStockSoruceDaySumDTO> implements InStockSoruceDaySumSer { 

 }