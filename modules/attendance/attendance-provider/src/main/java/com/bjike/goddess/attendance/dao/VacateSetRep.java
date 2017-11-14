package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.VacateSetDTO;
import com.bjike.goddess.attendance.entity.VacateSet;

/**
* 请假设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-12 01:46 ]
* @Description:	[ 请假设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface VacateSetRep extends JpaRep<VacateSet ,VacateSetDTO> { 

 }