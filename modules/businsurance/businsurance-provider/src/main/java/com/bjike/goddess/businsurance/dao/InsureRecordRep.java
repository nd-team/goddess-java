package com.bjike.goddess.businsurance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.entity.InsureRecord;

/**
* 意外险记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-22 05:43 ]
* @Description:	[ 意外险记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InsureRecordRep extends JpaRep<InsureRecord ,InsureRecordDTO> { 

 }