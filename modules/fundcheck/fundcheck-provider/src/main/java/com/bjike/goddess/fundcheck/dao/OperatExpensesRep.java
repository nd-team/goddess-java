package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.OperatExpensesDTO;
import com.bjike.goddess.fundcheck.entity.OperatExpenses;

/**
* 营业费用持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-07-01 01:54 ]
* @Description:	[ 营业费用持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OperatExpensesRep extends JpaRep<OperatExpenses ,OperatExpensesDTO> { 

 }