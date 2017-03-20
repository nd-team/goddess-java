package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 供应商类型管理业务传输对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-20T14:12:54.979 ]
* @Description:	[ 供应商类型管理业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SupplierTypeBO extends BaseBO { 

/**
* 名称
*/
 private String  name; 

/**
* 描述
*/
 private String  description; 

/**
* 状态
*/
 private Status  status; 



 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public String getDescription () { 
 return description;
 } 
 public void setDescription (String description ) { 
 this.description = description ; 
 } 
 public Status getStatus () { 
 return status;
 } 
 public void setStatus (Status status ) { 
 this.status = status ; 
 } 
 }