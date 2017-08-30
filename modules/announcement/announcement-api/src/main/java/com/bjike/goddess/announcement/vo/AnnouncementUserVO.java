package com.bjike.goddess.announcement.vo;

/**
* 公告对应的发布对象表现层对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-12 10:34 ]
* @Description:	[ 公告对应的发布对象表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AnnouncementUserVO { 

/**
* id
*/
 private String  id; 
/**
* 是否已经读取
*/
 private Boolean  haveRead; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public Boolean getHaveRead () { 
 return haveRead;
 } 
 public void setHaveRead (Boolean haveRead ) { 
 this.haveRead = haveRead ; 
 } 
 }