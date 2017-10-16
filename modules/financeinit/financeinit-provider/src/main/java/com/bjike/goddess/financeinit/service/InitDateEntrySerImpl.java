package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.financeinit.dto.InitDateEntryDTO;
import com.bjike.goddess.financeinit.entity.InitDateEntry;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 初始化数据录入业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 04:21 ]
* @Description:	[ 初始化数据录入业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="financeinitSerCache")
@Service
public class InitDateEntrySerImpl extends ServiceImpl<InitDateEntry, InitDateEntryDTO> implements InitDateEntrySer { 

 }