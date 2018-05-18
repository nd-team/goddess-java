package com.bjike.goddess.lendreimbursement.vo;

/**
* 报销-审核详情表现层对象
* @Author:			[ wany ]
* @Date:			[  2018-03-12 05:03 ]
* @Description:	[ 报销-审核详情表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AuditingByReimVO { 

/**
* id
*/
 private String  id; 
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

/**
* 报销金额
*/
 private double  reimbursement; 

/**
* 回退金额
*/
 private double  sendBack; 

/**
* 借款金额
*/
 private double  borrowMoney; 

/**
* 归还日期
*/
 private String  returnDate; 

/**
* 归还方式
*/
 private String  restitution; 

/**
* 归还账户
*/
 private String  account; 

/**
* 审核人模块
*/
 private String  auditorModule; 

/**
* 核对情况
*/
 private String  verification; 

/**
* 收票日期
*/
 private String  ticketDate; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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
 public double getReimbursement () { 
 return reimbursement;
 } 
 public void setReimbursement (double reimbursement ) { 
 this.reimbursement = reimbursement ; 
 } 
 public double getSendBack () { 
 return sendBack;
 } 
 public void setSendBack (double sendBack ) { 
 this.sendBack = sendBack ; 
 } 
 public double getBorrowMoney () { 
 return borrowMoney;
 } 
 public void setBorrowMoney (double borrowMoney ) { 
 this.borrowMoney = borrowMoney ; 
 } 
 public String getReturnDate () { 
 return returnDate;
 } 
 public void setReturnDate (String returnDate ) { 
 this.returnDate = returnDate ; 
 } 
 public String getRestitution () { 
 return restitution;
 } 
 public void setRestitution (String restitution ) { 
 this.restitution = restitution ; 
 } 
 public String getAccount () { 
 return account;
 } 
 public void setAccount (String account ) { 
 this.account = account ; 
 } 
 public String getAuditorModule () { 
 return auditorModule;
 } 
 public void setAuditorModule (String auditorModule ) { 
 this.auditorModule = auditorModule ; 
 } 
 public String getVerification () { 
 return verification;
 } 
 public void setVerification (String verification ) { 
 this.verification = verification ; 
 } 
 public String getTicketDate () { 
 return ticketDate;
 } 
 public void setTicketDate (String ticketDate ) { 
 this.ticketDate = ticketDate ; 
 } 
 }