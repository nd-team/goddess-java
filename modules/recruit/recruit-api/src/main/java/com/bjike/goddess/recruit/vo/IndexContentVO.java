package com.bjike.goddess.recruit.vo;

/**
* 考核指标内容表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-14 02:46 ]
* @Description:	[ 考核指标内容表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class IndexContentVO { 

/**
* id
*/
 private String  id; 
/**
* 对赌标签名
*/
 private String  name; 

/**
* 标签权重
*/
 private Integer  weight; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public Integer getWeight () { 
 return weight;
 } 
 public void setWeight (Integer weight ) { 
 this.weight = weight ; 
 } 
 }