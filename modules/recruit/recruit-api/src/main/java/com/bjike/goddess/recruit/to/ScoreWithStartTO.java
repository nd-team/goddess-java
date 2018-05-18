package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 打分
* @Author:			[ wanyi ]
* @Date:			[  2018-01-15 10:14 ]
* @Description:	[ 打分 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ScoreWithStartTO extends BaseTO { 

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