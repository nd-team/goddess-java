package com.bjike.goddess.lendreimbursement.vo;

/**
* 报销-寄件信息表现层对象
* @Author:			[ wany ]
* @Date:			[  2018-03-13 11:07 ]
* @Description:	[ 报销-寄件信息表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class mailingByReimVO { 

/**
* id
*/
 private String  id; 
/**
* 是否有发票
*/
 private boolean  invoice; 

/**
* 收件人
*/
 private String  addressee; 

/**
* 寄件时间
*/
 private String  time; 

/**
* 地区
*/
 private String  area; 

/**
* 详细地址
*/
 private String  address; 

/**
* 寄件备注
*/
 private String  remarks; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public boolean getInvoice () { 
 return invoice;
 } 
 public void setInvoice (boolean invoice ) { 
 this.invoice = invoice ; 
 } 
 public String getAddressee () { 
 return addressee;
 } 
 public void setAddressee (String addressee ) { 
 this.addressee = addressee ; 
 } 
 public String getTime () { 
 return time;
 } 
 public void setTime (String time ) { 
 this.time = time ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getAddress () { 
 return address;
 } 
 public void setAddress (String address ) { 
 this.address = address ; 
 } 
 public String getRemarks () { 
 return remarks;
 } 
 public void setRemarks (String remarks ) { 
 this.remarks = remarks ; 
 } 
 }