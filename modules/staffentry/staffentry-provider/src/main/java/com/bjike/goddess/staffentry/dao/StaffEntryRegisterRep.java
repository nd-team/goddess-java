package com.bjike.goddess.staffentry.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.StaffEntryRegister;

/**
* 员工入职注册持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-15 07:03 ]
* @Description:	[ 员工入职注册持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StaffEntryRegisterRep extends JpaRep<StaffEntryRegister ,StaffEntryRegisterDTO> { 

 }