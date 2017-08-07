package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.entity.Sellschedule;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 出售记录表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 10:15 ]
* @Description:	[ 出售记录表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="staffsharesSerCache")
@Service
public class SellscheduleSerImpl extends ServiceImpl<Sellschedule, SellscheduleDTO> implements SellscheduleSer { 

 }