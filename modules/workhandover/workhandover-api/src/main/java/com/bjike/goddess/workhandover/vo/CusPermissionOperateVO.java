package com.bjike.goddess.workhandover.vo;

/**
* 权限配置操作对象表现层对象
* @Author:			[ chenyang ]
* @Date:			[  2017-11-14 09:43 ]
* @Description:	[ 权限配置操作对象表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CusPermissionOperateVO { 

/**
* id
*/
 private String  id; 
/**
* 操作对象
*/
 private String  operator; 

/**
* 客户权限id
*/
 private String  cuspermissionId; 



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
 public String getCuspermissionId () { 
 return cuspermissionId;
 } 
 public void setCuspermissionId (String cuspermissionId ) { 
 this.cuspermissionId = cuspermissionId ; 
 } 
 }