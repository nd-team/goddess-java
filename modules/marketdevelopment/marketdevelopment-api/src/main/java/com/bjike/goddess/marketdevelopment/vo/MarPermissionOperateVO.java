package com.bjike.goddess.marketdevelopment.vo;

/**
* 市场计划进度管理权限配置操作对象表现层对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-31 11:36 ]
* @Description:	[ 市场计划进度管理权限配置操作对象表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class MarPermissionOperateVO { 

/**
* id
*/
 private String  id; 
/**
* 操作对象
*/
 private String  operator; 

/**
* 市场计划进度管理权限id
*/
 private String  marPermissionId; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getOperator () { 
 return operator;
 } 
 public void setOperator (String operator ) { 
 this.operator = operator ; 
 } 
 public String getMarPermissionId () { 
 return marPermissionId;
 } 
 public void setMarPermissionId (String marPermissionId ) { 
 this.marPermissionId = marPermissionId ; 
 } 
 }