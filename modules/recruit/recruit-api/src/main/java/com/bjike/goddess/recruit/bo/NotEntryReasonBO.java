package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 未入职原因业务传输对象
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-26 01:59 ]
* @Description:	[ 未入职原因业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class NotEntryReasonBO extends BaseBO { 

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