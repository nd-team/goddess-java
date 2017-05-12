package com.bjike.goddess.bankrecords.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bankrecords.dto.BankRecordDetailDTO;
import com.bjike.goddess.bankrecords.entity.BankRecordDetail;

/**
* 银行流水明细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-22 05:47 ]
* @Description:	[ 银行流水明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankRecordDetailRep extends JpaRep<BankRecordDetail ,BankRecordDetailDTO> { 

 }