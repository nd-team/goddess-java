package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 打分业务传输对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-15 10:14 ]
* @Description:	[ 打分业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ScoreWithStartBO extends BaseBO { 

/**
* 标签名称
*/
 private String  label; 

/**
* 分数
*/
 private String  score; 



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