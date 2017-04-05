package com.bjike.goddess.contacts.vo;

import com.bjike.goddess.common.api.type.Status;

/**
* 公共邮箱管理表现层对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-29 05:45 ]
* @Description:	[ 公共邮箱管理表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CommonalityVO { 

/**
* id
*/
 private String  id; 
/**
* 项目组/部门ID
*/
 private String  department_id; 

/**
* 邮箱地址
*/
 private String  email; 

/**
* 状态
*/
 private Status status;



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getDepartment_id () { 
 return department_id;
 } 
 public void setDepartment_id (String department_id ) { 
 this.department_id = department_id ; 
 } 
 public String getEmail () { 
 return email;
 } 
 public void setEmail (String email ) { 
 this.email = email ; 
 } 
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }