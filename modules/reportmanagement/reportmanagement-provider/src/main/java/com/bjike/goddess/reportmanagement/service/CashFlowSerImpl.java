package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashFlowDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlow;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 现金流量表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-21 03:54 ]
* @Description:	[ 现金流量表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class CashFlowSerImpl extends ServiceImpl<CashFlow, CashFlowDTO> implements CashFlowSer { 

 }