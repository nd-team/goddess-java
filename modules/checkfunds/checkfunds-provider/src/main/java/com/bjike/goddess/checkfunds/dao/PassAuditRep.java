package com.bjike.goddess.checkfunds.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkfunds.dto.PassAuditDTO;
import com.bjike.goddess.checkfunds.entity.PassAudit;

/**
* 已完成核对记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-10 04:18 ]
* @Description:	[ 已完成核对记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PassAuditRep extends JpaRep<PassAudit ,PassAuditDTO> { 

 }