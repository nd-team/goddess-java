package com.bjike.goddess.projectmarketfee.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 预警设计
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-09 04:53 ]
* @Description:	[ 预警设计 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WarnTO extends BaseTO { 

/**
* 预警的值
*/
 private Double  warnValue; 



 public Double getWarnValue () { 
 return warnValue;
 } 
 public void setWarnValue (Double warnValue ) { 
 this.warnValue = warnValue ; 
 } 
 }