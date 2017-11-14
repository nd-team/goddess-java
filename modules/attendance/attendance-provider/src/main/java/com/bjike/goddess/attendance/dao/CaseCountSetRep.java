package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.CaseCountSetDTO;
import com.bjike.goddess.attendance.entity.CaseCountSet;

/**
* 考勤情况汇总设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-19 06:18 ]
* @Description:	[ 考勤情况汇总设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CaseCountSetRep extends JpaRep<CaseCountSet ,CaseCountSetDTO> { 

 }