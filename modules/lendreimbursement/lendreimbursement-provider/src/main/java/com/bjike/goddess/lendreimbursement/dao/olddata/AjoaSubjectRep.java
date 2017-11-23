package com.bjike.goddess.lendreimbursement.dao.olddata;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaSubjectDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaSubject;

/**
* 老系统的科目持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-17 10:33 ]
* @Description:	[ 老系统的科目持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AjoaSubjectRep extends JpaRep<AjoaSubject ,AjoaSubjectDTO> { 

 }