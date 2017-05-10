package com.bjike.goddess.budget.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.entity.ProjectMonth;

/**
* 项目收入月持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-02 03:59 ]
* @Description:	[ 项目收入月持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectMonthRep extends JpaRep<ProjectMonth,ProjectMonthDTO> {

 }