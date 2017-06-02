package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.TransferHandlerYearSumDTO;
import com.bjike.goddess.materialsummary.entity.TransferHandlerYearSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 调动经手人年汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 11:56 ]
* @Description:	[ 调动经手人年汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class TransferHandlerYearSumSerImpl extends ServiceImpl<TransferHandlerYearSum, TransferHandlerYearSumDTO> implements TransferHandlerYearSumSer { 

 }