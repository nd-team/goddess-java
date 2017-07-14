package com.bjike.goddess.staffmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmeeting.dto.ReferPermissionDTO;
import com.bjike.goddess.staffmeeting.entity.ReferPermission;

/**
* 调阅权限持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-28 11:33 ]
* @Description:	[ 调阅权限持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ReferPermissionRep extends JpaRep<ReferPermission,ReferPermissionDTO> {

 }