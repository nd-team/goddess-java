package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.InStockAreaDaySumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 入库地区日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:13 ]
* @Description:	[ 入库地区日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class InStockAreaDaySumSerImpl extends ServiceImpl<InStockAreaDaySum, InStockAreaDaySumDTO> implements InStockAreaDaySumSer { 

 }