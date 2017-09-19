package com.bjike.goddess.assistance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesStandardDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidiesStandard;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 工龄补助标准业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-16 02:07 ]
* @Description:	[ 工龄补助标准业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="assistanceSerCache")
@Service
public class SenioritySubsidiesStandardSerImpl extends ServiceImpl<SenioritySubsidiesStandard, SenioritySubsidiesStandardDTO> implements SenioritySubsidiesStandardSer { 

 }