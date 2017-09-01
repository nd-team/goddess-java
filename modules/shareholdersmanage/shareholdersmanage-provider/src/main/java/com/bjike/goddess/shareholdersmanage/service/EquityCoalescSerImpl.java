package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.shareholdersmanage.dto.EquityCoalescDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityCoalesc;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 股权合并业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:24 ]
* @Description:	[ 股权合并业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="shareholdersmanageSerCache")
@Service
public class EquityCoalescSerImpl extends ServiceImpl<EquityCoalesc, EquityCoalescDTO> implements EquityCoalescSer { 

 }