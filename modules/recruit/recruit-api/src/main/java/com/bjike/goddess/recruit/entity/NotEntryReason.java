package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 未入职原因
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-26 01:59 ]
* @Description:	[ 未入职原因 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "recruit_notentryreason")
public class NotEntryReason extends BaseEntity { 

/**
* 汇总起始时间
*/
@Column(name = "notEntryReasonType",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '汇总起始时间'"  ) 
 private String  notEntryReasonType; 

/**
* 备注
*/
@Column(name = "comment",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '备注'"  ) 
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