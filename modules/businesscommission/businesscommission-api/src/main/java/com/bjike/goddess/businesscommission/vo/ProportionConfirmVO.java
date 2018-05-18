package com.bjike.goddess.businesscommission.vo;

/**
* 业务提成分配比例确认表表现层对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-23 04:13 ]
* @Description:	[ 业务提成分配比例确认表表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ProportionConfirmVO { 

/**
* id
*/
 private String  id; 
/**
* 业务提成分配比例表id
*/
 private String  proportionId; 

/**
* 参与协商人
*/
 private String  consultants; 

/**
* 是否确认
*/
 private Boolean  confirm; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getProportionId () { 
 return proportionId;
 } 
 public void setProportionId (String proportionId ) { 
 this.proportionId = proportionId ; 
 } 
 public String getConsultants () { 
 return consultants;
 } 
 public void setConsultants (String consultants ) { 
 this.consultants = consultants ; 
 } 
 public Boolean getConfirm () { 
 return confirm;
 } 
 public void setConfirm (Boolean confirm ) { 
 this.confirm = confirm ; 
 } 
 }