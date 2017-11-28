package com.bjike.goddess.workhandover.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workhandover.dto.CusPermissionDTO;
import com.bjike.goddess.workhandover.entity.CusPermission;

/**
* 权限配置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenyang ]
* @Date:			[  2017-11-14 09:33 ]
* @Description:	[ 权限配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CusPermissionRep extends JpaRep<CusPermission ,CusPermissionDTO> { 

 }