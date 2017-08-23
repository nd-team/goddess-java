package com.bjike.goddess.projectcost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectcost.dto.OtherExpensesDTO;
import com.bjike.goddess.projectcost.entity.OtherExpenses;

/**
* 其他费用持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-04 05:02 ]
* @Description:	[ 其他费用持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OtherExpensesRep extends JpaRep<OtherExpenses ,OtherExpensesDTO> { 

 }