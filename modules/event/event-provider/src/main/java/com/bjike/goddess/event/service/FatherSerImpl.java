package com.bjike.goddess.event.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.entity.Father;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 事件父表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-10 11:27 ]
* @Description:	[ 事件父表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="eventSerCache")
@Service
public class FatherSerImpl extends ServiceImpl<Father, FatherDTO> implements FatherSer { 

 }