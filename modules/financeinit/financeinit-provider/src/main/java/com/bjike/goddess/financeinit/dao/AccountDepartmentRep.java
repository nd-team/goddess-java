package com.bjike.goddess.financeinit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.entity.AccountDepartment;

/**
* 核算部门持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 02:43 ]
* @Description:	[ 核算部门持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AccountDepartmentRep extends JpaRep<AccountDepartment ,AccountDepartmentDTO> { 

 }