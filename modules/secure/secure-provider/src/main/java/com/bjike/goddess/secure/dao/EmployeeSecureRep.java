package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;

/**
* 员工社保基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-24 10:19 ]
* @Description:	[ 员工社保基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EmployeeSecureRep extends JpaRep<EmployeeSecure ,EmployeeSecureDTO> { 

 }