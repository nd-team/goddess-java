package com.bjike.goddess.allmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.allmeeting.dto.MultiPermissionDTO;
import com.bjike.goddess.allmeeting.entity.MultiPermission;

/**
* 调阅权限持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-28 03:21 ]
* @Description:	[ 调阅权限持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MultiPermissionRep extends JpaRep<MultiPermission ,MultiPermissionDTO> { 

 }