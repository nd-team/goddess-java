package com.bjike.goddess.projectmarketfee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectmarketfee.dto.GradeDTO;
import com.bjike.goddess.projectmarketfee.entity.Grade;

/**
* 等级设计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-09 04:55 ]
* @Description:	[ 等级设计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface GradeRep extends JpaRep<Grade ,GradeDTO> { 

 }