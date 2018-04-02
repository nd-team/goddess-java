package com.bjike.goddess.lendreimbursement.vo;

/**
* 报销表现层对象
* @Author:			[ wany ]
* @Date:			[  2018-03-12 03:02 ]
* @Description:	[ 报销表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ReimbursementVO { 

/**
* id
*/
 private String  id; 
/**
* 报销发生日期
*/
 private String  estimateTime; 

/**
* 报销人
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
* 单据数量
*/
 private Integer  quantityOfDocuments; 

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
* 报销当天任务
*/
 private String  reason; 

/**
* 补充内容
*/
 private String  supplementaryContent; 

/**
* 金额
*/
 private Double  money; 

/**
* 摘要
*/
 private String  invoice; 

/**
* 报销人备注
*/
 private String  noInvoice; 

/**
* 是否有发票
*/
 private boolean  readyMoney; 

/**
* 说明
*/
 private String  link; 

/**
* 无发票情况
*/
 private String  remarks; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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
 public Integer getQuantityOfDocuments () { 
 return quantityOfDocuments;
 } 
 public void setQuantityOfDocuments (Integer quantityOfDocuments ) { 
 this.quantityOfDocuments = quantityOfDocuments ; 
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
 public String getSupplementaryContent () { 
 return supplementaryContent;
 } 
 public void setSupplementaryContent (String supplementaryContent ) { 
 this.supplementaryContent = supplementaryContent ; 
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