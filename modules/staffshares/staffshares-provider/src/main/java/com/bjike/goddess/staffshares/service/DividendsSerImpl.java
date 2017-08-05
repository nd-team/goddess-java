package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.entity.Dividends;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 干股分红表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 11:14 ]
* @Description:	[ 干股分红表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="staffsharesSerCache")
@Service
public class DividendsSerImpl extends ServiceImpl<Dividends, DividendsDTO> implements DividendsSer { 

 }