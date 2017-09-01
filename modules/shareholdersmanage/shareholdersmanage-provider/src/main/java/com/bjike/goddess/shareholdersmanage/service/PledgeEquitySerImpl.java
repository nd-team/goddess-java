package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.shareholdersmanage.dto.PledgeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.PledgeEquity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 质押股权业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:32 ]
* @Description:	[ 质押股权业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="shareholdersmanageSerCache")
@Service
public class PledgeEquitySerImpl extends ServiceImpl<PledgeEquity, PledgeEquityDTO> implements PledgeEquitySer { 

 }