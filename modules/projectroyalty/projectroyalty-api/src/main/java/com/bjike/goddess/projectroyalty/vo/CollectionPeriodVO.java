package com.bjike.goddess.projectroyalty.vo;

/**
* 回款周期表现层对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-06-07 09:55 ]
* @Description:	[ 回款周期表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CollectionPeriodVO { 

/**
* id
*/
 private String  id; 
/**
* 回款周期(月)
*/
 private Integer  collection; 

/**
* 重要性
*/
 private Double  importance; 

/**
* 提成比例
*/
 private Double  rate; 

/**
* 资金方
*/
 private Double  capital; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public Integer getCollection () { 
 return collection;
 } 
 public void setCollection (Integer collection ) { 
 this.collection = collection ; 
 } 
 public Double getImportance () { 
 return importance;
 } 
 public void setImportance (Double importance ) { 
 this.importance = importance ; 
 } 
 public Double getRate () { 
 return rate;
 } 
 public void setRate (Double rate ) { 
 this.rate = rate ; 
 } 
 public Double getCapital () { 
 return capital;
 } 
 public void setCapital (Double capital ) { 
 this.capital = capital ; 
 } 
 }