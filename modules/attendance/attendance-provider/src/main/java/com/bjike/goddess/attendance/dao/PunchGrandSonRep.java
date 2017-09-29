package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.PunchGrandSonDTO;
import com.bjike.goddess.attendance.entity.PunchGrandSon;

/**
* 打卡孙子表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-22 05:13 ]
* @Description:	[ 打卡孙子表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PunchGrandSonRep extends JpaRep<PunchGrandSon ,PunchGrandSonDTO> { 

 }