package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.financeinit.dto.UseCommonlyDTO;
import com.bjike.goddess.financeinit.entity.UseCommonly;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 常用摘要业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 03:19 ]
* @Description:	[ 常用摘要业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="financeinitSerCache")
@Service
public class UseCommonlySerImpl extends ServiceImpl<UseCommonly, UseCommonlyDTO> implements UseCommonlySer { 

 }