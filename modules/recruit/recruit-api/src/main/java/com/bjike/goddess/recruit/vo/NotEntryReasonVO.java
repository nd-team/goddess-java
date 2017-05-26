package com.bjike.goddess.recruit.vo;

/**
* 未入职原因表现层对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-26 01:59 ]
* @Description:	[ 未入职原因表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class NotEntryReasonVO { 

/**
* id
*/
 private String  id; 
/**
* 汇总起始时间
*/
 private String  notEntryReasonType; 

/**
* 备注
*/
 private String  comment; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getNotEntryReasonType () { 
 return notEntryReasonType;
 } 
 public void setNotEntryReasonType (String notEntryReasonType ) { 
 this.notEntryReasonType = notEntryReasonType ; 
 } 
 public String getComment () { 
 return comment;
 } 
 public void setComment (String comment ) { 
 this.comment = comment ; 
 } 
 }