package com.bjike.goddess.marketdevelopment.vo;

/**
* 业务方向分类表现层对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-18 02:56 ]
* @Description:	[ 业务方向分类表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BusinessVO { 

/**
* id
*/
 private String  id; 
/**
* 业务方向编号
*/
 private String  businessNum; 

/**
* 业务方向分类
*/
 private String  businessType; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getBusinessNum () { 
 return businessNum;
 } 
 public void setBusinessNum (String businessNum ) { 
 this.businessNum = businessNum ; 
 } 
 public String getBusinessType () { 
 return businessType;
 } 
 public void setBusinessType (String businessType ) { 
 this.businessType = businessType ; 
 } 
 }