package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.customer.dto.ClosenessFoactorWeightDTO;
import com.bjike.goddess.customer.entity.ClosenessFoactorWeight;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 亲密度因素层权重业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-01 02:09 ]
* @Description:	[ 亲密度因素层权重业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="customerSerCache")
@Service
public class ClosenessFoactorWeightSerImpl extends ServiceImpl<ClosenessFoactorWeight, ClosenessFoactorWeightDTO> implements ClosenessFoactorWeightSer { 

 }