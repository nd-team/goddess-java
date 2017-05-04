package com.bjike.goddess.otherexpenses.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.otherexpenses.dto.OtherExpensesDTO;
import com.bjike.goddess.otherexpenses.entity.OtherExpenses;

/**
* 其他费用持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-03 11:49 ]
* @Description:	[ 其他费用持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OtherExpensesRep extends JpaRep<OtherExpenses ,OtherExpensesDTO> { 

 }