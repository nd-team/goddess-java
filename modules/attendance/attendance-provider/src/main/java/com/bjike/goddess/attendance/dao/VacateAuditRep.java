package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.VacateAuditDTO;
import com.bjike.goddess.attendance.entity.VacateAudit;

/**
* 请假审核表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-10 10:56 ]
* @Description:	[ 请假审核表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface VacateAuditRep extends JpaRep<VacateAudit ,VacateAuditDTO> { 

 }