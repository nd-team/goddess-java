package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 利润分析指标管理建议设计
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-30 05:24 ]
* @Description:	[ 利润分析指标管理建议设计 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ProfitIndicatorAdviceTO extends BaseTO { 

/**
* 销售毛利率最小值
*/
 private Double  grossProfitMin; 

/**
* 销售毛利率最大值
*/
 private Double  grossProfitMax; 

/**
* 销售净利率最小值
*/
 private Double  netProfitMin; 

/**
* 销售净利率最大值
*/
 private Double  netProfitMax; 

/**
* 资产净利润率最小值
*/
 private Double  assetProfitMin; 

/**
* 资产净利润率最大值
*/
 private Double  assetProfitMax; 

/**
* 净值报酬率最小值
*/
 private Double  rewardMin; 

/**
* 净值报酬率最大值
*/
 private Double  rewardMax; 

/**
* 管理建议
*/
 private String  advice; 



 public Double getGrossProfitMin () { 
 return grossProfitMin;
 } 
 public void setGrossProfitMin (Double grossProfitMin ) { 
 this.grossProfitMin = grossProfitMin ; 
 } 
 public Double getGrossProfitMax () { 
 return grossProfitMax;
 } 
 public void setGrossProfitMax (Double grossProfitMax ) { 
 this.grossProfitMax = grossProfitMax ; 
 } 
 public Double getNetProfitMin () { 
 return netProfitMin;
 } 
 public void setNetProfitMin (Double netProfitMin ) { 
 this.netProfitMin = netProfitMin ; 
 } 
 public Double getNetProfitMax () { 
 return netProfitMax;
 } 
 public void setNetProfitMax (Double netProfitMax ) { 
 this.netProfitMax = netProfitMax ; 
 } 
 public Double getAssetProfitMin () { 
 return assetProfitMin;
 } 
 public void setAssetProfitMin (Double assetProfitMin ) { 
 this.assetProfitMin = assetProfitMin ; 
 } 
 public Double getAssetProfitMax () { 
 return assetProfitMax;
 } 
 public void setAssetProfitMax (Double assetProfitMax ) { 
 this.assetProfitMax = assetProfitMax ; 
 } 
 public Double getRewardMin () { 
 return rewardMin;
 } 
 public void setRewardMin (Double rewardMin ) { 
 this.rewardMin = rewardMin ; 
 } 
 public Double getRewardMax () { 
 return rewardMax;
 } 
 public void setRewardMax (Double rewardMax ) { 
 this.rewardMax = rewardMax ; 
 } 
 public String getAdvice () { 
 return advice;
 } 
 public void setAdvice (String advice ) { 
 this.advice = advice ; 
 } 
 }