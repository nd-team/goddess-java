package com.bjike.goddess.assistance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assistance.dto.AssistanceRecordDTO;
import com.bjike.goddess.assistance.entity.AssistanceRecord;

/**
* 公司员工补助信息记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-14 10:07 ]
* @Description:	[ 公司员工补助信息记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AssistanceRecordRep extends JpaRep<AssistanceRecord ,AssistanceRecordDTO> { 

 }