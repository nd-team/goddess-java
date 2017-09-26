package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectroyalty.dto.WeightalTypeDTO;
import com.bjike.goddess.projectroyalty.entity.WeightalType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 项目提成权重分配类型表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-20 03:39 ]
* @Description:	[ 项目提成权重分配类型表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectroyaltySerCache")
@Service
public class WeightalTypeSerImpl extends ServiceImpl<WeightalType, WeightalTypeDTO> implements WeightalTypeSer { 

 }