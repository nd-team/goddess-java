package com.bjike.goddess.staffing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffing.dto.SalaryDTO;
import com.bjike.goddess.staffing.entity.Salary;

/**
* 工资区间持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-31 01:50 ]
* @Description:	[ 工资区间持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryRep extends JpaRep<Salary ,SalaryDTO> { 

 }