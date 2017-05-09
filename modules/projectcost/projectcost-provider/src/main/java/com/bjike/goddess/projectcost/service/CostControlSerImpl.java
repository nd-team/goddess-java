package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.entity.CostControl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 项目成本控制业务实现
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-04 05:56 ]
* @Description:	[ 项目成本控制业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectcostSerCache")
@Service
public class CostControlSerImpl extends ServiceImpl<CostControl, CostControlDTO> implements CostControlSer { 

 }