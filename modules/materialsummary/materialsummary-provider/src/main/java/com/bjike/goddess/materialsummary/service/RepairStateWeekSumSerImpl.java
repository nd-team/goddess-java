package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.RepairStateWeekSumDTO;
import com.bjike.goddess.materialsummary.entity.RepairStateWeekSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 维修状态周汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 01:49 ]
* @Description:	[ 维修状态周汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class RepairStateWeekSumSerImpl extends ServiceImpl<RepairStateWeekSum, RepairStateWeekSumDTO> implements RepairStateWeekSumSer { 

 }