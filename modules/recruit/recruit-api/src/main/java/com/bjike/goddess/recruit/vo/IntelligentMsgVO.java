package com.bjike.goddess.recruit.vo;

/**
* 智能消息提醒表现层对象
* @Author:			[ wany ]
* @Date:			[  2018-01-16 11:07 ]
* @Description:	[ 智能消息提醒表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class IntelligentMsgVO { 

/**
* id
*/
 private String  id; 
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



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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