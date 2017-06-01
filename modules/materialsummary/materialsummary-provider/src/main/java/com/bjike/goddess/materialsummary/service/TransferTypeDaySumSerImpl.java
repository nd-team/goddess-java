package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.TransferTypeDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferTypeDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调动类型日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:40 ]
* @Description:	[ 调动类型日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class TransferTypeDaySumSerImpl extends ServiceImpl<TransferTypeDaySum, TransferTypeDaySumDTO> implements TransferTypeDaySumSer { 

 }