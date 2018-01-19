package com.bjike.goddess.recruit.vo;

/**
* 打分表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-15 10:14 ]
* @Description:	[ 打分表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ScoreWithStartVO { 

/**
* id
*/
 private String  id; 
/**
* 标签名称
*/
 private String  label; 

/**
* 分数
*/
 private String  score; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getLabel () { 
 return label;
 } 
 public void setLabel (String label ) { 
 this.label = label ; 
 } 
 public String getScore () { 
 return score;
 } 
 public void setScore (String score ) { 
 this.score = score ; 
 } 
 }