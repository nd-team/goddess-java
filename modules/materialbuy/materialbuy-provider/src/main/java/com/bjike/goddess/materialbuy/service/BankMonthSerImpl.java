package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialbuy.dto.BankMonthDTO;
import com.bjike.goddess.materialbuy.entity.BankMonth;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 银行流水业务实现
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 04:29 ]
* @Description:	[ 银行流水业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialbuySerCache")
@Service
public class BankMonthSerImpl extends ServiceImpl<BankMonth, BankMonthDTO> implements BankMonthSer { 

 }