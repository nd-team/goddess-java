package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.AlertIndexDTO;
import com.bjike.goddess.recruit.entity.AlertIndex;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 弹框考核指标业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-14 02:43 ]
* @Description:	[ 弹框考核指标业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class AlertIndexSerImpl extends ServiceImpl<AlertIndex, AlertIndexDTO> implements AlertIndexSer { 

 }