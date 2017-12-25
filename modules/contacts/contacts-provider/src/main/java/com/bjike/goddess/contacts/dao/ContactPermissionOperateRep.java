package com.bjike.goddess.contacts.dao;

import com.bjike.goddess.contacts.dto.ContactPermissionOperateDTO;
import com.bjike.goddess.contacts.entity.ContactPermissionOperate;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
* 客户权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-25 02:12 ]
* @Description:	[ 客户权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContactPermissionOperateRep extends JpaRep<ContactPermissionOperate,ContactPermissionOperateDTO> {

 }