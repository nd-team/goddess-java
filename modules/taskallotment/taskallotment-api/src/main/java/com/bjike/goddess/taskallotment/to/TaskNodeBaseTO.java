package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
* 计划任务节点集合
* @Author:			[ chenyang ]
* @Date:			[  2018-01-18 11:58 ]
* @Description:	[ 计划任务节点集合 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class TaskNodeBaseTO extends BaseTO { 

/**
* tasknode
*/
 private List<TaskNodeTO> taskNodeList;


 public List<TaskNodeTO> getTaskNodeList() {
  return taskNodeList;
 }

 public void setTaskNodeList(List<TaskNodeTO> taskNodeList) {
  this.taskNodeList = taskNodeList;
 }
}