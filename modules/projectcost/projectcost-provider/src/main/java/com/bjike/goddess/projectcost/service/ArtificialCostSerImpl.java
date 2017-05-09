package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectcost.dto.ArtificialCostDTO;
import com.bjike.goddess.projectcost.entity.ArtificialCost;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 人工费用业务实现
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-04 05:19 ]
* @Description:	[ 人工费用业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectcostSerCache")
@Service
public class ArtificialCostSerImpl extends ServiceImpl<ArtificialCost, ArtificialCostDTO> implements ArtificialCostSer { 

 }