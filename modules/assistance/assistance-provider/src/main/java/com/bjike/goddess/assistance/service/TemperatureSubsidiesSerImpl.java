package com.bjike.goddess.assistance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.assistance.dto.TemperatureSubsidiesDTO;
import com.bjike.goddess.assistance.entity.TemperatureSubsidies;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 工龄补助标准业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-16 02:38 ]
* @Description:	[ 工龄补助标准业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="assistanceSerCache")
@Service
public class TemperatureSubsidiesSerImpl extends ServiceImpl<TemperatureSubsidies, TemperatureSubsidiesDTO> implements TemperatureSubsidiesSer { 

 }