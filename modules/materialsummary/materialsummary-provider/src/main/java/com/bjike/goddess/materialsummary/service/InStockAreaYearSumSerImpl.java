package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.InStockAreaYearSumDTO;
import com.bjike.goddess.materialsummary.entity.InStockAreaYearSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 入库地区年汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:11 ]
* @Description:	[ 入库地区年汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class InStockAreaYearSumSerImpl extends ServiceImpl<InStockAreaYearSum, InStockAreaYearSumDTO> implements InStockAreaYearSumSer { 

 }