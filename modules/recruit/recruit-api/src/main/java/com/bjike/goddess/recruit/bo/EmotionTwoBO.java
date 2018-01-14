package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.entity.EmotionThree;

import javax.persistence.Column;
/**
* 情感标签二级业务传输对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:56 ]
* @Description:	[ 情感标签二级业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class EmotionTwoBO extends BaseBO { 

/**
* 标签名称
*/
 private String  labelName; 

/**
* 三级标签
*/
 private EmotionThree emotionThree;



 public String getLabelName () { 
 return labelName;
 } 
 public void setLabelName (String labelName ) { 
 this.labelName = labelName ; 
 } 
 public EmotionThree getEmotionThree () { 
 return emotionThree;
 } 
 public void setEmotionThree (EmotionThree emotionThree ) { 
 this.emotionThree = emotionThree ; 
 } 
 }