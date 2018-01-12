package com.bjike.goddess.abilitydisplay.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 公司项目
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 03:05 ]
* @Description:	[ 公司项目 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ComProjectTO extends BaseTO { 

/**
* 项目名称
*/
 private String  name; 

/**
* 项目进度
*/
 private String  progress; 



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