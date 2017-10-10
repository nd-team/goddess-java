package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.entity.DayReport;

/**
* 日报持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-07 10:28 ]
* @Description:	[ 日报持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DayReportRep extends JpaRep<DayReport ,DayReportDTO> { 

 }