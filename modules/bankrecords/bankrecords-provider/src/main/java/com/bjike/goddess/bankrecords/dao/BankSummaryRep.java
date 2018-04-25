package com.bjike.goddess.bankrecords.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bankrecords.dto.BankSummaryDTO;
import com.bjike.goddess.bankrecords.entity.BankSummary;

/**
* 银行流水持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-08 10:27 ]
* @Description:	[ 银行流水持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankSummaryRep extends JpaRep<BankSummary ,BankSummaryDTO> { 

 }