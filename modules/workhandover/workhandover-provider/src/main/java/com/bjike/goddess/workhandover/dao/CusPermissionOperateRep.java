package com.bjike.goddess.workhandover.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workhandover.dto.CusPermissionOperateDTO;
import com.bjike.goddess.workhandover.entity.CusPermissionOperate;

/**
* 权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenyang ]
* @Date:			[  2017-11-14 09:43 ]
* @Description:	[ 权限配置操作对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CusPermissionOperateRep extends JpaRep<CusPermissionOperate ,CusPermissionOperateDTO> { 

 }