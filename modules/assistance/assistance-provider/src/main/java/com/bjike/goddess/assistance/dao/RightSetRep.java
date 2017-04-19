package com.bjike.goddess.assistance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assistance.dto.RightSetDTO;
import com.bjike.goddess.assistance.entity.RightSet;

/**
* 权限设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-14 10:14 ]
* @Description:	[ 权限设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RightSetRep extends JpaRep<RightSet ,RightSetDTO> { 

 }