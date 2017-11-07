package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.VisitSchedule;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 拜访日程表业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-03 03:13 ]
* @Description:	[ 拜访日程表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="customerSerCache")
@Service
public class VisitScheduleSerImpl extends ServiceImpl<VisitSchedule, VisitScheduleDTO> implements VisitScheduleSer { 

 }