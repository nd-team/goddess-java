package com.bjike.goddess.lendreimbursement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordLogDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecordLog;

/**
* 报销记录日志表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-11 05:48 ]
* @Description:	[ 报销记录日志表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ReimburseRecordLogRep extends JpaRep<ReimburseRecordLog ,ReimburseRecordLogDTO> { 

 }