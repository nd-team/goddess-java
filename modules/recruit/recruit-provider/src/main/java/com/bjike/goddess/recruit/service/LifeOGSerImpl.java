package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.LifeOGDTO;
import com.bjike.goddess.recruit.entity.LifeOG;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 工作对赌业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:11 ]
* @Description:	[ 工作对赌业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class LifeOGSerImpl extends ServiceImpl<LifeOG, LifeOGDTO> implements LifeOGSer { 

 }