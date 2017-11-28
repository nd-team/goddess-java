package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 现金流量分析
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-23 11:43 ]
* @Description:	[ 现金流量分析 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "reportmanagement_cashanalyse")
public class CashAnalyse extends BaseEntity { 

/**
* 项目
*/
@Column(name = "project",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目'"  ) 
 private String  project; 

/**
* 金额
*/
@Column(name = "money",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '金额'"  ) 
 private String  money; 

/**
* 比率
*/
@Column(name = "rate",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '比率'"  ) 
 private String  rate; 

/**
* 分析
*/
@Column(name = "analyse",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '分析'"  ) 
 private String  analyse; 

/**
* 管理建议
*/
@Column(name = "advice",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '管理建议'"  ) 
 private String  advice; 



 public String getProject () { 
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public String getMoney () { 
 return money;
 } 
 public void setMoney (String money ) { 
 this.money = money ; 
 } 
 public String getRate () { 
 return rate;
 } 
 public void setRate (String rate ) { 
 this.rate = rate ; 
 } 
 public String getAnalyse () { 
 return analyse;
 } 
 public void setAnalyse (String analyse ) { 
 this.analyse = analyse ; 
 } 
 public String getAdvice () { 
 return advice;
 } 
 public void setAdvice (String advice ) { 
 this.advice = advice ; 
 } 
 }