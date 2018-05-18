package com.bjike.goddess.attendance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attendance.dto.PunchGrandSonDTO;
import com.bjike.goddess.attendance.entity.PunchGrandSon;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 打卡孙子表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-22 05:13 ]
* @Description:	[ 打卡孙子表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attendanceSerCache")
@Service
public class PunchGrandSonSerImpl extends ServiceImpl<PunchGrandSon, PunchGrandSonDTO> implements PunchGrandSonSer { 

 }