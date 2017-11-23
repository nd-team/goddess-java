package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashRateDTO;
import com.bjike.goddess.reportmanagement.entity.CashRate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 现金流量比率表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-20 05:15 ]
* @Description:	[ 现金流量比率表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class CashRateSerImpl extends ServiceImpl<CashRate, CashRateDTO> implements CashRateSer { 

 }