package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.RemoveEmployee;

/**
* 减员名单持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-24 09:48 ]
* @Description:	[ 减员名单持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RemoveEmployeeRep extends JpaRep<RemoveEmployee ,RemoveEmployeeDTO> { 

 }