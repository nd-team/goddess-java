package com.bjike.goddess.task.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.task.dto.CustomizeSonDTO;
import com.bjike.goddess.task.entity.CustomizeSon;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 自定义汇总子表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-18 11:09 ]
* @Description:	[ 自定义汇总子表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="taskSerCache")
@Service
public class CustomizeSonSerImpl extends ServiceImpl<CustomizeSon, CustomizeSonDTO> implements CustomizeSonSer { 

 }