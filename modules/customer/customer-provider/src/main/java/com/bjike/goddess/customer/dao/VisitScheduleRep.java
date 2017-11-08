package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.VisitSchedule;

/**
* 拜访日程表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-03 03:13 ]
* @Description:	[ 拜访日程表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface VisitScheduleRep extends JpaRep<VisitSchedule ,VisitScheduleDTO> { 

 }