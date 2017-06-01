package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.SupPermissionDTO;
import com.bjike.goddess.supplier.entity.SupPermission;

/**
* 供应商权限配置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-27 10:43 ]
* @Description:	[ 供应商权限配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SupPermissionRep extends JpaRep<SupPermission ,SupPermissionDTO> { 

 }