package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 未入职原因
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-26 01:59 ]
* @Description:	[ 未入职原因 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class NotEntryReasonTO extends BaseTO { 

/**
* 汇总起始时间
*/
 private String  notEntryReasonType; 

/**
* 备注
*/
 private String  comment; 



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