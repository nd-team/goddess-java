package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.dto.WorkRangeFlatsDTO;
import com.bjike.goddess.organize.entity.WorkRangeFlats;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 工作范围信息设置平台业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-06 10:58 ]
* @Description:	[ 工作范围信息设置平台业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="organizeSerCache")
@Service
public class WorkRangeFlatsSerImpl extends ServiceImpl<WorkRangeFlats, WorkRangeFlatsDTO> implements WorkRangeFlatsSer { 

 }