package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.recruit.entity.EmotionTwo;

/**
* 情感标签二级表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:57 ]
* @Description:	[ 情感标签二级表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class EmotionOneVO { 

/**
* id
*/
 private String  id; 
/**
* 标签名称
*/
 private String  labelName; 

/**
* 二级标签
*/
 private EmotionTwo emotionTwo;



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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