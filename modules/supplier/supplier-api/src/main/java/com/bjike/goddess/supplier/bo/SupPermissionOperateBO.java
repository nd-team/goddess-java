package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 供应商权限配置操作对象业务传输对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-27 11:22 ]
* @Description:	[ 供应商权限配置操作对象业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SupPermissionOperateBO extends BaseBO { 

/**
* 操作对象
*/
 private String  operator; 

/**
* 供应商权限id
*/
 private String  supPermissionId; 



 public String getOperator () { 
 return operator;
 } 
 public void setOperator (String operator ) { 
 this.operator = operator ; 
 } 
 public String getSupPermissionId () { 
 return supPermissionId;
 } 
 public void setSupPermissionId (String supPermissionId ) { 
 this.supPermissionId = supPermissionId ; 
 } 
 }