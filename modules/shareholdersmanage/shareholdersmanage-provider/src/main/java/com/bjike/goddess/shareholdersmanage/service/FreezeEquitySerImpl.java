package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.shareholdersmanage.dto.FreezeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.FreezeEquity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 冻结股权业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:39 ]
* @Description:	[ 冻结股权业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="shareholdersmanageSerCache")
@Service
public class FreezeEquitySerImpl extends ServiceImpl<FreezeEquity, FreezeEquityDTO> implements FreezeEquitySer { 

 }