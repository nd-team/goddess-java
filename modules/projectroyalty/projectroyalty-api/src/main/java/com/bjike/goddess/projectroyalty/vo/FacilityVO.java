package com.bjike.goddess.projectroyalty.vo;

/**
* 难易度表现层对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-06-07 09:47 ]
* @Description:	[ 难易度表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class FacilityVO { 

/**
* id
*/
 private String  id; 
/**
* 难易度
*/
 private Double  facility; 

/**
* 重要性
*/
 private Double  importance; 

/**
* 提成比例
*/
 private Double  rate; 

/**
* 管理能力
*/
 private Double  manage; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public Double getFacility () { 
 return facility;
 } 
 public void setFacility (Double facility ) { 
 this.facility = facility ; 
 } 
 public Double getImportance () { 
 return importance;
 } 
 public void setImportance (Double importance ) { 
 this.importance = importance ; 
 } 
 public Double getRate () { 
 return rate;
 } 
 public void setRate (Double rate ) { 
 this.rate = rate ; 
 } 
 public Double getManage () { 
 return manage;
 } 
 public void setManage (Double manage ) { 
 this.manage = manage ; 
 } 
 }