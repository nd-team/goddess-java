package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.AuditingByReimDTO;
import com.bjike.goddess.lendreimbursement.entity.AuditingByReim;

/**
* 报销-审核详情持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wany ]
* @Date:			[  2018-03-12 05:03 ]
* @Description:	[ 报销-审核详情持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AuditingByReimRep extends JpaRep<AuditingByReim ,AuditingByReimDTO> { 

 }