package com.bjike.goddess.assistance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidies;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 工龄补助业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-16 11:34 ]
* @Description:	[ 工龄补助业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="assistanceSerCache")
@Service
public class SenioritySubsidiesSerImpl extends ServiceImpl<SenioritySubsidies, SenioritySubsidiesDTO> implements SenioritySubsidiesSer { 

 }