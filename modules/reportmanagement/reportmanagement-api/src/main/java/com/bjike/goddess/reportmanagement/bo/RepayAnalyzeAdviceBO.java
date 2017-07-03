package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 偿还能力分析管理建议设计业务传输对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-30 04:04 ]
* @Description:	[ 偿还能力分析管理建议设计业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class RepayAnalyzeAdviceBO extends BaseBO { 

/**
* 流动率最小值
*/
 private Double  flowMin; 

/**
* 流动率最大值
*/
 private Double  flowMax; 

/**
* 速动率最小值
*/
 private Double  rateMin; 

/**
* 速动率最大值
*/
 private Double  rateMax; 

/**
* 现金率最小值
*/
 private Double  moneyMin; 

/**
* 现金率最大值
*/
 private Double  moneyMax; 

/**
* 资产负债率最小值
*/
 private Double  assestMin; 

/**
* 资产负债率最大值
*/
 private Double  assestMax; 

/**
* 产权率最小值
*/
 private Double  equityMin; 

/**
* 产权率最大值
*/
 private Double  equityMax; 

/**
* 管理建议
*/
 private String  advice; 



 public Double getFlowMin () { 
 return flowMin;
 } 
 public void setFlowMin (Double flowMin ) { 
 this.flowMin = flowMin ; 
 } 
 public Double getFlowMax () { 
 return flowMax;
 } 
 public void setFlowMax (Double flowMax ) { 
 this.flowMax = flowMax ; 
 } 
 public Double getRateMin () { 
 return rateMin;
 } 
 public void setRateMin (Double rateMin ) { 
 this.rateMin = rateMin ; 
 } 
 public Double getRateMax () { 
 return rateMax;
 } 
 public void setRateMax (Double rateMax ) { 
 this.rateMax = rateMax ; 
 } 
 public Double getMoneyMin () { 
 return moneyMin;
 } 
 public void setMoneyMin (Double moneyMin ) { 
 this.moneyMin = moneyMin ; 
 } 
 public Double getMoneyMax () { 
 return moneyMax;
 } 
 public void setMoneyMax (Double moneyMax ) { 
 this.moneyMax = moneyMax ; 
 } 
 public Double getAssestMin () { 
 return assestMin;
 } 
 public void setAssestMin (Double assestMin ) { 
 this.assestMin = assestMin ; 
 } 
 public Double getAssestMax () { 
 return assestMax;
 } 
 public void setAssestMax (Double assestMax ) { 
 this.assestMax = assestMax ; 
 } 
 public Double getEquityMin () { 
 return equityMin;
 } 
 public void setEquityMin (Double equityMin ) { 
 this.equityMin = equityMin ; 
 } 
 public Double getEquityMax () { 
 return equityMax;
 } 
 public void setEquityMax (Double equityMax ) { 
 this.equityMax = equityMax ; 
 } 
 public String getAdvice () { 
 return advice;
 } 
 public void setAdvice (String advice ) { 
 this.advice = advice ; 
 } 
 }