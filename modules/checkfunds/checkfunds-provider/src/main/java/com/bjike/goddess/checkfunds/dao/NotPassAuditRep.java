package com.bjike.goddess.checkfunds.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkfunds.dto.NotPassAuditDTO;
import com.bjike.goddess.checkfunds.entity.NotPassAudit;

/**
* 审批不通过记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-10 04:23 ]
* @Description:	[ 审批不通过记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface NotPassAuditRep extends JpaRep<NotPassAudit ,NotPassAuditDTO> { 

 }