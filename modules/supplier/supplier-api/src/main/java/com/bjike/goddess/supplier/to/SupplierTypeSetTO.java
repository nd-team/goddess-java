package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 供应商类型设置
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:07 ]
* @Description:	[ 供应商类型设置 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SupplierTypeSetTO extends BaseTO { 

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