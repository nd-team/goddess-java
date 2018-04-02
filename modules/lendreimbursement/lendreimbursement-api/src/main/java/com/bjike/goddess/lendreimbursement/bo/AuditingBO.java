package com.bjike.goddess.lendreimbursement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 审核详情业务传输对象
* @Author:			[ wany ]
* @Date:			[  2018-03-02 09:39 ]
* @Description:	[ 审核详情业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AuditingBO extends BaseBO { 

/**
* 职位名称
*/
 private String  position; 

/**
* 审核人
*/
 private String  auditor; 

/**
* 审核意见
*/
 private String  opinion; 

/**
* 审核时间
*/
 private String  time; 

/**
* 是否通过
*/
 private boolean  pass; 



 public String getPosition () { 
 return position;
 } 
 public void setPosition (String position ) { 
 this.position = position ; 
 } 
 public String getAuditor () { 
 return auditor;
 } 
 public void setAuditor (String auditor ) { 
 this.auditor = auditor ; 
 } 
 public String getOpinion () { 
 return opinion;
 } 
 public void setOpinion (String opinion ) { 
 this.opinion = opinion ; 
 } 
 public String getTime () { 
 return time;
 } 
 public void setTime (String time ) { 
 this.time = time ; 
 } 
 public boolean getPass () { 
 return pass;
 } 
 public void setPass (boolean pass ) { 
 this.pass = pass ; 
 } 
 }