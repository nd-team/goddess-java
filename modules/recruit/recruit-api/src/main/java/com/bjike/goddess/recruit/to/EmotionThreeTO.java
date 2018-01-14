package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 情感标签三级
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:52 ]
* @Description:	[ 情感标签三级 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class EmotionThreeTO extends BaseTO { 

/**
* 标签名称
*/
 private String  labelName; 



 public String getLabelName () { 
 return labelName;
 } 
 public void setLabelName (String labelName ) { 
 this.labelName = labelName ; 
 } 
 }