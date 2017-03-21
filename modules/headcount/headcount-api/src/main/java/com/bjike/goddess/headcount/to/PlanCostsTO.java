package com.bjike.goddess.headcount.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 计划人工成本信息
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T16:48:23.645 ]
* @Description:	[ 计划人工成本信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PlanCostsTO extends BaseTO { 

/**
* 总计划人工成本
*/
 private Double  total; 

/**
* 公司整体平均工资水平
*/
 private Double  avg; 

/**
* 公司总人数配置
*/
 private Integer  number; 

/**
* 职能体系占比
*/
 private Double  funPercent; 

/**
* 实施体系占比
*/
 private Double  implePercent; 



 public Double getTotal () { 
 return total;
 } 
 public void setTotal (Double total ) { 
 this.total = total ; 
 } 
 public Double getAvg () { 
 return avg;
 } 
 public void setAvg (Double avg ) { 
 this.avg = avg ; 
 } 
 public Integer getNumber () { 
 return number;
 } 
 public void setNumber (Integer number ) { 
 this.number = number ; 
 } 
 public Double getFunPercent () { 
 return funPercent;
 } 
 public void setFunPercent (Double funPercent ) { 
 this.funPercent = funPercent ; 
 } 
 public Double getImplePercent () { 
 return implePercent;
 } 
 public void setImplePercent (Double implePercent ) { 
 this.implePercent = implePercent ; 
 } 
 }