package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusDaySumDTO;
import com.bjike.goddess.materialsummary.entity.AreaBuyStatusDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 地区购买情况日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:51 ]
* @Description:	[ 地区购买情况日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class AreaBuyStatusDaySumSerImpl extends ServiceImpl<AreaBuyStatusDaySum, AreaBuyStatusDaySumDTO> implements AreaBuyStatusDaySumSer { 

 }