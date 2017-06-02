package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionDTO;
import com.bjike.goddess.marketdevelopment.entity.MarPermission;

/**
* 市场计划进度管理权限配置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-31 11:37 ]
* @Description:	[ 市场计划进度管理权限配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MarPermissionRep extends JpaRep<MarPermission ,MarPermissionDTO> { 

 }