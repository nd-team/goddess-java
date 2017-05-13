package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
/**
* 公共邮箱管理业务传输对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-29 05:45 ]
* @Description:	[ 公共邮箱管理业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CommonalityBO extends BaseBO { 

/**
* 项目组/部门ID
*/
 private String  departmentId; 

/**
* 邮箱地址
*/
 private String  email; 

/**
* 状态
*/
 private Status status;



 public String getDepartmentId () { 
 return departmentId;
 } 
 public void setDepartmentId (String departmentId ) { 
 this.departmentId = departmentId ; 
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