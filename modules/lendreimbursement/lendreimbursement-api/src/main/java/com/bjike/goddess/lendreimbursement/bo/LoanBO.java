package com.bjike.goddess.lendreimbursement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 借款业务传输对象
* @Author:			[ wany ]
* @Date:			[  2018-03-01 05:48 ]
* @Description:	[ 借款业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class LoanBO extends BaseBO { 

/**
* 预计借款时间
*/
 private String  estimateTime; 

/**
* 借款人
*/
 private String  borrower; 

/**
* 负责人
*/
 private String  charge; 

/**
* 参与人
*/
 private String  participants; 

/**
* 地区
*/
 private String  area; 

/**
* 项目组
*/
 private String  project; 

/**
* 项目名称
*/
 private String  projectName; 

/**
* 借款方式
*/
 private String  wayOfBorrowing; 

/**
* 对赌总比例
*/
 private String  proportionOG; 

/**
* 一级科目
*/
 private String  classA; 

/**
* 二级科目
*/
 private String  classB; 

/**
* 三级科目
*/
 private String  classC; 

/**
* 是否补写
*/
 private boolean  writeUp; 

/**
* 借款事由
*/
 private String  reason; 

/**
* 金额
*/
 private Double  money; 

/**
* 是否有发票
*/
 private String  invoice; 

/**
* 无发票备注
*/
 private String  noInvoice; 

/**
* 是否有准备金
*/
 private boolean  readyMoney; 

/**
* 商品连接
*/
 private String  link; 

/**
* 备注
*/
 private String  remarks; 



 public String getEstimateTime () { 
 return estimateTime;
 } 
 public void setEstimateTime (String estimateTime ) { 
 this.estimateTime = estimateTime ; 
 } 
 public String getBorrower () { 
 return borrower;
 } 
 public void setBorrower (String borrower ) { 
 this.borrower = borrower ; 
 } 
 public String getCharge () { 
 return charge;
 } 
 public void setCharge (String charge ) { 
 this.charge = charge ; 
 } 
 public String getParticipants () { 
 return participants;
 } 
 public void setParticipants (String participants ) { 
 this.participants = participants ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getProject () { 
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public String getProjectName () { 
 return projectName;
 } 
 public void setProjectName (String projectName ) { 
 this.projectName = projectName ; 
 } 
 public String getWayOfBorrowing () { 
 return wayOfBorrowing;
 } 
 public void setWayOfBorrowing (String wayOfBorrowing ) { 
 this.wayOfBorrowing = wayOfBorrowing ; 
 } 
 public String getProportionOG () { 
 return proportionOG;
 } 
 public void setProportionOG (String proportionOG ) { 
 this.proportionOG = proportionOG ; 
 } 
 public String getClassA () { 
 return classA;
 } 
 public void setClassA (String classA ) { 
 this.classA = classA ; 
 } 
 public String getClassB () { 
 return classB;
 } 
 public void setClassB (String classB ) { 
 this.classB = classB ; 
 } 
 public String getClassC () { 
 return classC;
 } 
 public void setClassC (String classC ) { 
 this.classC = classC ; 
 } 
 public boolean getWriteUp () { 
 return writeUp;
 } 
 public void setWriteUp (boolean writeUp ) { 
 this.writeUp = writeUp ; 
 } 
 public String getReason () { 
 return reason;
 } 
 public void setReason (String reason ) { 
 this.reason = reason ; 
 } 
 public Double getMoney () { 
 return money;
 } 
 public void setMoney (Double money ) { 
 this.money = money ; 
 } 
 public String getInvoice () { 
 return invoice;
 } 
 public void setInvoice (String invoice ) { 
 this.invoice = invoice ; 
 } 
 public String getNoInvoice () { 
 return noInvoice;
 } 
 public void setNoInvoice (String noInvoice ) { 
 this.noInvoice = noInvoice ; 
 } 
 public boolean getReadyMoney () { 
 return readyMoney;
 } 
 public void setReadyMoney (boolean readyMoney ) { 
 this.readyMoney = readyMoney ; 
 } 
 public String getLink () { 
 return link;
 } 
 public void setLink (String link ) { 
 this.link = link ; 
 } 
 public String getRemarks () { 
 return remarks;
 } 
 public void setRemarks (String remarks ) { 
 this.remarks = remarks ; 
 } 
 }