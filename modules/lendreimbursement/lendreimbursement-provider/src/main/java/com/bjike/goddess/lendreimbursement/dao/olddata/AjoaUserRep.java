package com.bjike.goddess.lendreimbursement.dao.olddata;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaUserDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaUser;

/**
* 老系统的报销持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-21 12:03 ]
* @Description:	[ 老系统的报销持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AjoaUserRep extends JpaRep<AjoaUser ,AjoaUserDTO> { 

 }