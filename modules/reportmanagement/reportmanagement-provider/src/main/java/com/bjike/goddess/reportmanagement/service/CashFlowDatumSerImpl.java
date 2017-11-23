package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashFlowDatumDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlowDatum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 补充资料业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-22 11:54 ]
* @Description:	[ 补充资料业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class CashFlowDatumSerImpl extends ServiceImpl<CashFlowDatum, CashFlowDatumDTO> implements CashFlowDatumSer { 

 }