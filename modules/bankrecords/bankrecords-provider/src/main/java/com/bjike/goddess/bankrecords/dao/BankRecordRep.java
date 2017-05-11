package com.bjike.goddess.bankrecords.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.entity.BankRecord;

/**
* 银行流水持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-22 05:35 ]
* @Description:	[ 银行流水持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankRecordRep extends JpaRep<BankRecord ,BankRecordDTO> { 

 }