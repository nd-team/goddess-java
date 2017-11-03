package com.bjike.goddess.customer.vo;

/**
* 难易度因素层权重表现层对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-01 02:20 ]
* @Description:	[ 难易度因素层权重表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class DifficultyFoactorWeightVO { 

/**
* id
*/
 private String  id; 
/**
* 困难度类型名称
*/
 private String  difficName; 

/**
* 困难度类型权重
*/
 private Double  difficWeight; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getDifficName () { 
 return difficName;
 } 
 public void setDifficName (String difficName ) { 
 this.difficName = difficName ; 
 } 
 public Double getDifficWeight () { 
 return difficWeight;
 } 
 public void setDifficWeight (Double difficWeight ) { 
 this.difficWeight = difficWeight ; 
 } 
 }