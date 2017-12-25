package com.bjike.goddess.contacts.dao;

import com.bjike.goddess.contacts.dto.ContactPermissionDTO;
import com.bjike.goddess.contacts.entity.ContactPermission;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
* 客户权限配置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-12 05:43 ]
* @Description:	[ 客户权限配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContactPermissionRep extends JpaRep<ContactPermission,ContactPermissionDTO> {

 }