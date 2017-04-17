package com.bjike.goddess.assistance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assistance.dto.AssistanceEmpDTO;
import com.bjike.goddess.assistance.entity.AssistanceEmp;

/**
* 补助员工名单持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-14 10:12 ]
* @Description:	[ 补助员工名单持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AssistanceEmpRep extends JpaRep<AssistanceEmp ,AssistanceEmpDTO> { 

 }