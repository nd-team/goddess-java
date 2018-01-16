package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.WorkOGDTO;
import com.bjike.goddess.recruit.entity.WorkOG;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 工作对赌业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 02:33 ]
* @Description:	[ 工作对赌业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class WorkOGSerImpl extends ServiceImpl<WorkOG, WorkOGDTO> implements WorkOGSer { 

 }