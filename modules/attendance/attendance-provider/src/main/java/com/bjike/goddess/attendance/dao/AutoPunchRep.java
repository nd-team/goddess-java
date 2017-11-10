package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.AutoPunchDTO;
import com.bjike.goddess.attendance.entity.AutoPunch;

/**
* 自动打卡持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-06 09:22 ]
* @Description:	[ 自动打卡持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AutoPunchRep extends JpaRep<AutoPunch ,AutoPunchDTO> { 

 }