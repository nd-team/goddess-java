package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 供应商类型设置业务传输对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:07 ]
* @Description:	[ 供应商类型设置业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SupplierTypeSetBO extends BaseBO { 

/**
* 供应商类型
*/
 private String  supplierType; 



 public String getSupplierType () { 
 return supplierType;
 } 
 public void setSupplierType (String supplierType ) { 
 this.supplierType = supplierType ; 
 } 
 }