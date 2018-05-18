package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.ArrestPointDTO;
import com.bjike.goddess.attendance.entity.ArrestPoint;

/**
* 驻点设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-22 03:12 ]
* @Description:	[ 驻点设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ArrestPointRep extends JpaRep<ArrestPoint ,ArrestPointDTO> { 

 }