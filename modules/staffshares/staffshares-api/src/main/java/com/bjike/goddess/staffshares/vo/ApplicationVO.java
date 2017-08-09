package com.bjike.goddess.staffshares.vo;

/**
* 干股代表申请表现层对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 10:27 ]
* @Description:	[ 干股代表申请表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ApplicationVO { 

/**
* id
*/
 private String  id; 
/**
* 持股人
*/
 private String  shareholder; 

/**
* 申请时间
*/
 private LocalDateTime  time; 

/**
* 地区
*/
 private String  area; 

/**
* 项目组/部门
*/
 private String  department; 

/**
* 岗位
*/
 private String  position; 

/**
* 在职时间（月数）
*/
 private int  months; 

/**
* 申请原因
*/
 private String  reason; 

/**
* 备注
*/
 private String  remark; 

/**
* 财务运营部负责人
*/
 private String  financial; 

/**
* 财务运营部审核意见
*/
 private String  opinion; 

/**
* 规划模块
*/
 private String  planModule; 

/**
* 规划模块审核意见
*/
 private String  opinion1; 

/**
* 总经办
*/
 private String  manager; 

/**
* 总经办审核意见
*/
 private String  opinion2; 

/**
* 投票数
*/
 private int  votes; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getShareholder () { 
 return shareholder;
 } 
 public void setShareholder (String shareholder ) { 
 this.shareholder = shareholder ; 
 } 
 public LocalDateTime getTime () { 
 return time;
 } 
 public void setTime (LocalDateTime time ) { 
 this.time = time ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getDepartment () { 
 return department;
 } 
 public void setDepartment (String department ) { 
 this.department = department ; 
 } 
 public String getPosition () { 
 return position;
 } 
 public void setPosition (String position ) { 
 this.position = position ; 
 } 
 public int getMonths () { 
 return months;
 } 
 public void setMonths (int months ) { 
 this.months = months ; 
 } 
 public String getReason () { 
 return reason;
 } 
 public void setReason (String reason ) { 
 this.reason = reason ; 
 } 
 public String getRemark () { 
 return remark;
 } 
 public void setRemark (String remark ) { 
 this.remark = remark ; 
 } 
 public String getFinancial () { 
 return financial;
 } 
 public void setFinancial (String financial ) { 
 this.financial = financial ; 
 } 
 public String getOpinion () { 
 return opinion;
 } 
 public void setOpinion (String opinion ) { 
 this.opinion = opinion ; 
 } 
 public String getPlanModule () { 
 return planModule;
 } 
 public void setPlanModule (String planModule ) { 
 this.planModule = planModule ; 
 } 
 public String getOpinion1 () { 
 return opinion1;
 } 
 public void setOpinion1 (String opinion1 ) { 
 this.opinion1 = opinion1 ; 
 } 
 public String getManager () { 
 return manager;
 } 
 public void setManager (String manager ) { 
 this.manager = manager ; 
 } 
 public String getOpinion2 () { 
 return opinion2;
 } 
 public void setOpinion2 (String opinion2 ) { 
 this.opinion2 = opinion2 ; 
 } 
 public int getVotes () { 
 return votes;
 } 
 public void setVotes (int votes ) { 
 this.votes = votes ; 
 } 
 }