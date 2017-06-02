package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.TransferHandlerDaySumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调动经手人日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:53 ]
* @Description:	[ 调动经手人日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class TransferHandlerDaySumSerImpl extends ServiceImpl<TransferHandlerDaySum, TransferHandlerDaySumDTO> implements TransferHandlerDaySumSer { 

 }