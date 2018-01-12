package com.bjike.goddess.abilitydisplay.vo;

/**
* 公司项目表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 03:05 ]
* @Description:	[ 公司项目表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ComProjectVO { 

/**
* id
*/
 private String  id; 
/**
* 项目名称
*/
 private String  name; 

/**
* 项目进度
*/
 private String  progress; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public String getProgress () { 
 return progress;
 } 
 public void setProgress (String progress ) { 
 this.progress = progress ; 
 } 
 }