package com.bjike.goddess.budget.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.entity.ProjectWeek;

/**
* 项目收入周持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-02 03:58 ]
* @Description:	[ 项目收入周持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectWeekRep extends JpaRep<ProjectWeek,ProjectWeekDTO> {

 }