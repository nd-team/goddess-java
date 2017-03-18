package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.entity.CusFamilyMember;

/**
* 客户家庭成员持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-16T09:48:29.113 ]
* @Description:	[ 客户家庭成员持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CusFamilyMemberRep extends JpaRep<CusFamilyMember ,CusFamilyMemberDTO> { 

 }