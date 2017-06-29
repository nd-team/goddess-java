package com.bjike.goddess.allmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.allmeeting.dto.ConcisePermissionDTO;
import com.bjike.goddess.allmeeting.entity.ConcisePermission;

/**
* 调阅权限持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-28 11:33 ]
* @Description:	[ 调阅权限持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConcisePermissionRep extends JpaRep<ConcisePermission ,ConcisePermissionDTO> { 

 }