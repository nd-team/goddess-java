package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.DayCountSetDTO;
import com.bjike.goddess.attendance.entity.DayCountSet;

/**
* 日报汇总设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 04:20 ]
* @Description:	[ 日报汇总设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DayCountSetRep extends JpaRep<DayCountSet ,DayCountSetDTO> { 

 }