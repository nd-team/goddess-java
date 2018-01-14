package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 情感标签三级业务传输对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:52 ]
* @Description:	[ 情感标签三级业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class EmotionThreeBO extends BaseBO { 

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