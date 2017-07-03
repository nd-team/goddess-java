package com.bjike.goddess.staffmeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 议题管理业务传输对象
* @Author:			[ Jason ]
* @Date:			[  2017-06-03 03:31 ]
* @Description:	[ 议题管理业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class MeetingTopicBO extends BaseBO { 

/**
* 议题
*/
 private String  topic; 

/**
* 议题包含内容
*/
 private String  topicContent; 



 public String getTopic () { 
 return topic;
 } 
 public void setTopic (String topic ) { 
 this.topic = topic ; 
 } 
 public String getTopicContent () { 
 return topicContent;
 } 
 public void setTopicContent (String topicContent ) { 
 this.topicContent = topicContent ; 
 } 
 }