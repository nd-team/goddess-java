package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.taskallotment.bo.TaskNodeBO;

import java.util.List;

/**
* 计划任务节点集合表现层对象
* @Author:			[ chenyang ]
* @Date:			[  2018-01-18 11:58 ]
* @Description:	[ 计划任务节点集合表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class TaskNodeBaseVO { 

/**
* id
*/
 private String  id; 
/**
* taskNodeList
*/
 private List<TaskNodeBO> taskNodeList;



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 }

 public List<TaskNodeBO> getTaskNodeList() {
  return taskNodeList;
 }

 public void setTaskNodeList(List<TaskNodeBO> taskNodeList) {
  this.taskNodeList = taskNodeList;
 }
}