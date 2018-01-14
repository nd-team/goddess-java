package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.recruit.entity.EmotionTwo;

/**
* 情感标签二级
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:57 ]
* @Description:	[ 情感标签二级 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class EmotionOneTO extends BaseTO { 

/**
* 标签名称
*/
 private String  labelName; 

/**
* 二级标签
*/
 private EmotionTwo emotionTwo;



 public String getLabelName () { 
 return labelName;
 } 
 public void setLabelName (String labelName ) { 
 this.labelName = labelName ; 
 } 
 public EmotionTwo getEmotionTwo () { 
 return emotionTwo;
 } 
 public void setEmotionTwo (EmotionTwo emotionTwo ) { 
 this.emotionTwo = emotionTwo ; 
 } 
 }