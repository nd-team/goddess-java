package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.AuditTimeSetDTO;
import com.bjike.goddess.attendance.entity.AuditTimeSet;

/**
* 审批时间设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 04:37 ]
* @Description:	[ 审批时间设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AuditTimeSetRep extends JpaRep<AuditTimeSet ,AuditTimeSetDTO> { 

 }