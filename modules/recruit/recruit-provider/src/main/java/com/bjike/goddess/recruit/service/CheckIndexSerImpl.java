package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.CheckIndexDTO;
import com.bjike.goddess.recruit.entity.CheckIndex;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 考核指标业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:26 ]
* @Description:	[ 考核指标业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class CheckIndexSerImpl extends ServiceImpl<CheckIndex, CheckIndexDTO> implements CheckIndexSer { 

 }