package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.entity.Vacate;

/**
* 请假管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-07 05:15 ]
* @Description:	[ 请假管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface VacateRep extends JpaRep<Vacate ,VacateDTO> { 

 }