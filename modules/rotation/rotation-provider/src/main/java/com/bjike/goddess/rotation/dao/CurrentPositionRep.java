package com.bjike.goddess.rotation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rotation.dto.CurrentPositionDTO;
import com.bjike.goddess.rotation.entity.CurrentPosition;

/**
* 目前岗位情况持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2018-01-08 09:30 ]
* @Description:	[ 目前岗位情况持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CurrentPositionRep extends JpaRep<CurrentPosition,CurrentPositionDTO> {

 }