package com.bjike.goddess.attendance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attendance.dto.ExtralOverWorkSonDTO;
import com.bjike.goddess.attendance.entity.ExtralOverWorkSon;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 补班子表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-21 10:32 ]
* @Description:	[ 补班子表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attendanceSerCache")
@Service
public class ExtralOverWorkSonSerImpl extends ServiceImpl<ExtralOverWorkSon, ExtralOverWorkSonDTO> implements ExtralOverWorkSonSer { 

 }