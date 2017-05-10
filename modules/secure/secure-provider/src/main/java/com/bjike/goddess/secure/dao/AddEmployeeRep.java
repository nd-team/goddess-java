package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.entity.AddEmployee;

/**
* 社保增员信息名单持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-21 03:02 ]
* @Description:	[ 社保增员信息名单持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AddEmployeeRep extends JpaRep<AddEmployee ,AddEmployeeDTO> { 

 }