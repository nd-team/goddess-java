package com.bjike.goddess.managementpromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managementpromotion.dto.GradeLevelDTO;
import com.bjike.goddess.managementpromotion.entity.GradeLevel;

/**
* 管理等级定级持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-22 01:57 ]
* @Description:	[ 管理等级定级持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface GradeLevelRep extends JpaRep<GradeLevel ,GradeLevelDTO> { 

 }