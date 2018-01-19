package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 弹框考核指标
* @Author:			[ wanyi ]
* @Date:			[  2018-01-14 02:43 ]
* @Description:	[ 弹框考核指标 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AlertIndexTO extends BaseTO { 

/**
* 考核指标名称
*/
 private String  name; 



 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 }