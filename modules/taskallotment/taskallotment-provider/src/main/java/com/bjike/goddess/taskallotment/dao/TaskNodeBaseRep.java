package com.bjike.goddess.taskallotment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.taskallotment.dto.TaskNodeBaseDTO;
import com.bjike.goddess.taskallotment.entity.TaskNodeBase;

/**
* 计划任务节点集合持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenyang ]
* @Date:			[  2018-01-18 11:58 ]
* @Description:	[ 计划任务节点集合持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TaskNodeBaseRep extends JpaRep<TaskNodeBase,TaskNodeBaseDTO> {

 }