package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.mailingByReimDTO;
import com.bjike.goddess.lendreimbursement.entity.mailingByReim;

/**
* 报销-寄件信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wany ]
* @Date:			[  2018-03-13 11:07 ]
* @Description:	[ 报销-寄件信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface mailingByReimRep extends JpaRep<mailingByReim ,mailingByReimDTO> { 

 }