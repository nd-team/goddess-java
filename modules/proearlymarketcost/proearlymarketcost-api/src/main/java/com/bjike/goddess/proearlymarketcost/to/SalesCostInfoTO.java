package com.bjike.goddess.proearlymarketcost.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 销售费用信息
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:36 ]
* @Description:	[ 销售费用信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalesCostInfoTO extends BaseTO { 

/**
* 日期
*/
 private String  date;

/**
* 地区
*/
 private String  area; 

/**
* 项目名称
*/
 private String  projectName; 

/**
* 用户名称
*/
 private String  userName; 

/**
* 分类
*/
 private String  type; 

/**
* 一级科目(总账科目)
*/
 private String  oneSubject; 

/**
* 二级科目
*/
 private String  twoSubject; 

/**
* 三级科目(明细科目)
*/
 private String  threeSubject; 

/**
* 摘要
*/
 private String  digest; 

/**
* 借方金额
*/
 private Double  debitMoney; 

/**
* 贷方金额
*/
 private Double  creditMoney;


 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }

 public String getArea () {
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getProjectName () { 
 return projectName;
 } 
 public void setProjectName (String projectName ) { 
 this.projectName = projectName ; 
 } 
 public String getUserName () { 
 return userName;
 } 
 public void setUserName (String userName ) { 
 this.userName = userName ; 
 } 
 public String getType () { 
 return type;
 } 
 public void setType (String type ) { 
 this.type = type ; 
 } 
 public String getOneSubject () { 
 return oneSubject;
 } 
 public void setOneSubject (String oneSubject ) { 
 this.oneSubject = oneSubject ; 
 } 
 public String getTwoSubject () { 
 return twoSubject;
 } 
 public void setTwoSubject (String twoSubject ) { 
 this.twoSubject = twoSubject ; 
 } 
 public String getThreeSubject () { 
 return threeSubject;
 } 
 public void setThreeSubject (String threeSubject ) { 
 this.threeSubject = threeSubject ; 
 } 
 public String getDigest () { 
 return digest;
 } 
 public void setDigest (String digest ) { 
 this.digest = digest ; 
 } 
 public Double getDebitMoney () { 
 return debitMoney;
 } 
 public void setDebitMoney (Double debitMoney ) { 
 this.debitMoney = debitMoney ; 
 } 
 public Double getCreditMoney () { 
 return creditMoney;
 } 
 public void setCreditMoney (Double creditMoney ) { 
 this.creditMoney = creditMoney ; 
 } 
 }