package com.bjike.goddess.task.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.task.dto.SchemeSonDTO;
import com.bjike.goddess.task.entity.SchemeSon;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 汇总方案子表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-18 04:56 ]
* @Description:	[ 汇总方案子表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="taskSerCache")
@Service
public class SchemeSonSerImpl extends ServiceImpl<SchemeSon, SchemeSonDTO> implements SchemeSonSer { 

 }