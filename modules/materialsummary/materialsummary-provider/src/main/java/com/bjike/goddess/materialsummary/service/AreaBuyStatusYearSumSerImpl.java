package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusYearSumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusYearSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 地区购买情况年汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:56 ]
* @Description:	[ 地区购买情况年汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class AreaBuyStatusYearSumSerImpl extends ServiceImpl<AreaBuyStatusYearSum, AreaBuyStatusYearSumDTO> implements AreaBuyStatusYearSumSer { 

 }