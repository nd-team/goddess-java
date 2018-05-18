package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.PhoneRemindDTO;
import com.bjike.goddess.attendance.entity.PhoneRemind;

/**
* 手机提醒打卡持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 09:28 ]
* @Description:	[ 手机提醒打卡持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PhoneRemindRep extends JpaRep<PhoneRemind ,PhoneRemindDTO> { 

 }