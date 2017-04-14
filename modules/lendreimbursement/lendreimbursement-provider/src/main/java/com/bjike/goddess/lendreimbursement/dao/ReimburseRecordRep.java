package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecord;

/**
* 报销记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-11 05:42 ]
* @Description:	[ 报销记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ReimburseRecordRep extends JpaRep<ReimburseRecord ,ReimburseRecordDTO> { 

 }