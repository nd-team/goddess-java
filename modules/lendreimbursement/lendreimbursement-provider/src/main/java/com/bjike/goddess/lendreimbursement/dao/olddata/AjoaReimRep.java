package com.bjike.goddess.lendreimbursement.dao.olddata;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaReimDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaReim;

/**
* 老系统的报销持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-17 01:55 ]
* @Description:	[ 老系统的报销持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AjoaReimRep extends JpaRep<AjoaReim ,AjoaReimDTO> { 

 }