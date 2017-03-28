package com.bjike.goddess.proearlymarketcost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 费用效益分析
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:58 ]
* @Description:	[ 费用效益分析 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "proearlymarketcost_costbenefitanalysis")
public class CostBenefitAnalysis extends BaseEntity { 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 

/**
* 年份
*/
@Column(name = "year",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '年份'"  ) 
 private String  year; 

/**
* 月份
*/
@Column(name = "month",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '月份'"  ) 
 private String  month; 

/**
* 项目名称
*/
@Column(name = "projectName",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目名称'"  ) 
 private String  projectName; 

/**
* 预计收入
*/
@Column(name = "expectedRevenue",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '预计收入'"  ) 
 private Double  expectedRevenue; 

/**
* 预计市场费
*/
@Column(name = "expectedMarketCost",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '预计市场费'"  ) 
 private Double  expectedMarketCost; 

/**
* 实际市场费
*/
@Column(name = "actualMarketCost",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际市场费'"  ) 
 private Double  actualMarketCost; 

/**
* 差额
*/
@Column(name = "difference",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '差额'"  ) 
 private Double  difference; 

/**
* 预计占比
*/
@Column(name = "expectedAccounted",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '预计占比'"  ) 
 private Double  expectedAccounted; 

/**
* 实际占比
*/
@Column(name = "actualAccounted",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '实际占比'"  ) 
 private Double  actualAccounted; 

/**
* 预警
*/
@Column(name = "warning",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '预警'"  ) 
 private String  warning; 

/**
* 等级评定
*/
@Column(name = "rating",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '等级评定'"  ) 
 private String  rating; 



 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getYear () { 
 return year;
 } 
 public void setYear (String year ) { 
 this.year = year ; 
 } 
 public String getMonth () { 
 return month;
 } 
 public void setMonth (String month ) { 
 this.month = month ; 
 } 
 public String getProjectName () { 
 return projectName;
 } 
 public void setProjectName (String projectName ) { 
 this.projectName = projectName ; 
 } 
 public Double getExpectedRevenue () { 
 return expectedRevenue;
 } 
 public void setExpectedRevenue (Double expectedRevenue ) { 
 this.expectedRevenue = expectedRevenue ; 
 } 
 public Double getExpectedMarketCost () { 
 return expectedMarketCost;
 } 
 public void setExpectedMarketCost (Double expectedMarketCost ) { 
 this.expectedMarketCost = expectedMarketCost ; 
 } 
 public Double getActualMarketCost () { 
 return actualMarketCost;
 } 
 public void setActualMarketCost (Double actualMarketCost ) { 
 this.actualMarketCost = actualMarketCost ; 
 } 
 public Double getDifference () { 
 return difference;
 } 
 public void setDifference (Double difference ) { 
 this.difference = difference ; 
 } 
 public Double getExpectedAccounted () { 
 return expectedAccounted;
 } 
 public void setExpectedAccounted (Double expectedAccounted ) { 
 this.expectedAccounted = expectedAccounted ; 
 } 
 public Double getActualAccounted () { 
 return actualAccounted;
 } 
 public void setActualAccounted (Double actualAccounted ) { 
 this.actualAccounted = actualAccounted ; 
 } 
 public String getWarning () { 
 return warning;
 } 
 public void setWarning (String warning ) { 
 this.warning = warning ; 
 } 
 public String getRating () { 
 return rating;
 } 
 public void setRating (String rating ) { 
 this.rating = rating ; 
 } 
 }