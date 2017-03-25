package com.bjike.goddess.proearlymarketcost.vo;

/**
* 费用效益分析表现层对象
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:58 ]
* @Description:	[ 费用效益分析表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CostBenefitAnalysisVO { 

/**
* id
*/
 private String  id; 
/**
* 地区
*/
 private String  area; 

/**
* 年份
*/
 private String  year; 

/**
* 月份
*/
 private String  month; 

/**
* 项目名称
*/
 private String  projectName; 

/**
* 预计收入
*/
 private Double  expectedRevenue; 

/**
* 预计市场费
*/
 private Double  expectedMarketCost; 

/**
* 实际市场费
*/
 private Double  actualMarketCost; 

/**
* 差额
*/
 private Double  difference; 

/**
* 预计占比
*/
 private Double  expectedAccounted; 

/**
* 实际占比
*/
 private Double  actualAccounted; 

/**
* 预警
*/
 private String  warning; 

/**
* 等级评定
*/
 private String  rating; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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