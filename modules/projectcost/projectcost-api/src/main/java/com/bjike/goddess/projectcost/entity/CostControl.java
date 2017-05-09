package com.bjike.goddess.projectcost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 项目成本控制
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-04 05:56 ]
* @Description:	[ 项目成本控制 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "projectcost_costcontrol")
public class CostControl extends BaseEntity { 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 

/**
* 项目名称
*/
@Column(name = "name",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目名称'"  ) 
 private String  name; 

/**
* 年份
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '年份'"  ) 
 private Integer  year; 

/**
* 月份
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '月份'"  ) 
 private Integer  month; 

/**
* 目标人工时数
*/
@Column(name = "targetIncome",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '目标人工时数'"  ) 
 private Double  targetIncome; 

/**
* 实际人工时数
*/
@Column(name = "actualIncome",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工时数'"  ) 
 private Double  actualIncome; 

/**
* 目标人工成本
*/
@Column(name = "targetCost",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '目标人工成本'"  ) 
 private Double  targetCost; 

/**
* 实际人工成本
*/
@Column(name = "actualCost",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工成本'"  ) 
 private Double  actualCost; 

/**
* 目标车辆费用
*/
@Column(name = "targetCar",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '目标车辆费用'"  ) 
 private Double  targetCar; 

/**
* 实际车辆费用
*/
@Column(name = "actualCar",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际车辆费用'"  ) 
 private Double  actualCar; 

/**
* 目标其他费用
*/
@Column(name = "targetOther",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '目标其他费用'"  ) 
 private Double  targetOther; 

/**
* 实际其他费用
*/
@Column(name = "actualOther",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际其他费用'"  ) 
 private Double  actualOther; 

/**
* 目标费用合计
*/
@Column(name = "targetTotal",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '目标费用合计'"  ) 
 private Double  targetTotal; 

/**
* 实际费用合计
*/
@Column(name = "actualTotal",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际费用合计'"  ) 
 private Double  actualTotal; 

/**
* 目标利润率
*/
@Column(name = "targetProfit",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '目标利润率'"  ) 
 private Double  targetProfit; 

/**
* 实际利润率
*/
@Column(name = "actualProfit",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际利润率'"  ) 
 private Double  actualProfit; 



 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public Integer getYear () { 
 return year;
 } 
 public void setYear (Integer year ) { 
 this.year = year ; 
 } 
 public Integer getMonth () { 
 return month;
 } 
 public void setMonth (Integer month ) { 
 this.month = month ; 
 } 
 public Double getTargetIncome () { 
 return targetIncome;
 } 
 public void setTargetIncome (Double targetIncome ) { 
 this.targetIncome = targetIncome ; 
 } 
 public Double getActualIncome () { 
 return actualIncome;
 } 
 public void setActualIncome (Double actualIncome ) { 
 this.actualIncome = actualIncome ; 
 } 
 public Double getTargetCost () { 
 return targetCost;
 } 
 public void setTargetCost (Double targetCost ) { 
 this.targetCost = targetCost ; 
 } 
 public Double getActualCost () { 
 return actualCost;
 } 
 public void setActualCost (Double actualCost ) { 
 this.actualCost = actualCost ; 
 } 
 public Double getTargetCar () { 
 return targetCar;
 } 
 public void setTargetCar (Double targetCar ) { 
 this.targetCar = targetCar ; 
 } 
 public Double getActualCar () { 
 return actualCar;
 } 
 public void setActualCar (Double actualCar ) { 
 this.actualCar = actualCar ; 
 } 
 public Double getTargetOther () { 
 return targetOther;
 } 
 public void setTargetOther (Double targetOther ) { 
 this.targetOther = targetOther ; 
 } 
 public Double getActualOther () { 
 return actualOther;
 } 
 public void setActualOther (Double actualOther ) { 
 this.actualOther = actualOther ; 
 } 
 public Double getTargetTotal () { 
 return targetTotal;
 } 
 public void setTargetTotal (Double targetTotal ) { 
 this.targetTotal = targetTotal ; 
 } 
 public Double getActualTotal () { 
 return actualTotal;
 } 
 public void setActualTotal (Double actualTotal ) { 
 this.actualTotal = actualTotal ; 
 } 
 public Double getTargetProfit () { 
 return targetProfit;
 } 
 public void setTargetProfit (Double targetProfit ) { 
 this.targetProfit = targetProfit ; 
 } 
 public Double getActualProfit () { 
 return actualProfit;
 } 
 public void setActualProfit (Double actualProfit ) { 
 this.actualProfit = actualProfit ; 
 } 
 }