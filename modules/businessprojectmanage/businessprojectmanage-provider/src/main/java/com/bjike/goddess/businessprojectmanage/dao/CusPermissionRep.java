package com.bjike.goddess.businessprojectmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessprojectmanage.dto.CusPermissionDTO;
import com.bjike.goddess.businessprojectmanage.entity.CusPermission;

/**
* 客户权限设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-12 05:56 ]
* @Description:	[ 客户权限设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CusPermissionRep extends JpaRep<CusPermission ,CusPermissionDTO> { 

 }