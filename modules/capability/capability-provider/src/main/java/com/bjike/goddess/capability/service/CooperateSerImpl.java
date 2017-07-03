package com.bjike.goddess.capability.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CooperateDTO;
import com.bjike.goddess.capability.entity.Cooperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 公司合作对象业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:03 ]
* @Description:	[ 公司合作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="capabilitySerCache")
@Service
public class CooperateSerImpl extends ServiceImpl<Cooperate, CooperateDTO> implements CooperateSer { 

 }