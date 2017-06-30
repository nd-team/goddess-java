package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 负债与权益结构管理建议设计
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-30 04:56 ]
* @Description:	[ 负债与权益结构管理建议设计 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class DebtStructureAdviceTO extends BaseTO { 

/**
* 流动负债比例最小值
*/
 private Double  flowMin; 

/**
* 流动负债比例最大值
*/
 private Double  flowMax; 

/**
* 长期负债比例最小值
*/
 private Double  longMin; 

/**
* 长期负债比例最大值
*/
 private Double  longMax; 

/**
* 所有者权益比例最小值
*/
 private Double  allMin; 

/**
* 所有者权益比例最大值
*/
 private Double  allMax; 

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
 public Double getLongMin () { 
 return longMin;
 } 
 public void setLongMin (Double longMin ) { 
 this.longMin = longMin ; 
 } 
 public Double getLongMax () { 
 return longMax;
 } 
 public void setLongMax (Double longMax ) { 
 this.longMax = longMax ; 
 } 
 public Double getAllMin () { 
 return allMin;
 } 
 public void setAllMin (Double allMin ) { 
 this.allMin = allMin ; 
 } 
 public Double getAllMax () { 
 return allMax;
 } 
 public void setAllMax (Double allMax ) { 
 this.allMax = allMax ; 
 } 
 public String getAdvice () { 
 return advice;
 } 
 public void setAdvice (String advice ) { 
 this.advice = advice ; 
 } 
 }