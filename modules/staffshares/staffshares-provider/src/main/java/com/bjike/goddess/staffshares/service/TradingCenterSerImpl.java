package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffshares.dto.TradingCenterDTO;
import com.bjike.goddess.staffshares.entity.TradingCenter;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 交易中心业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 09:05 ]
* @Description:	[ 交易中心业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="staffsharesSerCache")
@Service
public class TradingCenterSerImpl extends ServiceImpl<TradingCenter, TradingCenterDTO> implements TradingCenterSer { 

 }