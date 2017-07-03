package com.bjike.goddess.checkfunds.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkfunds.dto.BankReconciliationDTO;
import com.bjike.goddess.checkfunds.entity.BankReconciliation;

/**
* 银企对账（核对）持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-10 03:53 ]
* @Description:	[ 银企对账（核对）持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankReconciliationRep extends JpaRep<BankReconciliation ,BankReconciliationDTO> { 

 }