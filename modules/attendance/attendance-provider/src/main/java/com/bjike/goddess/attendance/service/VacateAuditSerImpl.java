package com.bjike.goddess.attendance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attendance.dto.VacateAuditDTO;
import com.bjike.goddess.attendance.entity.VacateAudit;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 请假审核表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-10 10:56 ]
* @Description:	[ 请假审核表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="attendanceSerCache")
@Service
public class VacateAuditSerImpl extends ServiceImpl<VacateAudit, VacateAuditDTO> implements VacateAuditSer { 

 }