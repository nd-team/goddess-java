package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 智能消息提醒
* @Author:			[ wany ]
* @Date:			[  2018-01-16 11:07 ]
* @Description:	[ 智能消息提醒 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class IntelligentMsgTO extends BaseTO { 

/**
* 发送时间
*/
 private String  time; 

/**
* 提醒内容
*/
 private String  content; 

/**
* 用户
*/
 private String  user; 



 public String getTime () { 
 return time;
 } 
 public void setTime (String time ) { 
 this.time = time ; 
 } 
 public String getContent () { 
 return content;
 } 
 public void setContent (String content ) { 
 this.content = content ; 
 } 
 public String getUser () { 
 return user;
 } 
 public void setUser (String user ) { 
 this.user = user ; 
 } 
 }