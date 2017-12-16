package com.bjike.goddess.supplier.vo;

/**
* 联络情况表现层对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:03 ]
* @Description:	[ 联络情况表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContactSituationVO { 

/**
* id
*/
 private String  id; 
/**
* 业务联络人
*/
 private String  bussLiaison; 

/**
* 职务
*/
 private String  duty; 

/**
* 联系电话
*/
 private String  contactNum; 

/**
* 邮箱
*/
 private String  email; 

/**
* 传真
*/
 private String  facsimile; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getBussLiaison () { 
 return bussLiaison;
 } 
 public void setBussLiaison (String bussLiaison ) { 
 this.bussLiaison = bussLiaison ; 
 } 
 public String getDuty () { 
 return duty;
 } 
 public void setDuty (String duty ) { 
 this.duty = duty ; 
 } 
 public String getContactNum () { 
 return contactNum;
 } 
 public void setContactNum (String contactNum ) { 
 this.contactNum = contactNum ; 
 } 
 public String getEmail () { 
 return email;
 } 
 public void setEmail (String email ) { 
 this.email = email ; 
 } 
 public String getFacsimile () { 
 return facsimile;
 } 
 public void setFacsimile (String facsimile ) { 
 this.facsimile = facsimile ; 
 } 
 }