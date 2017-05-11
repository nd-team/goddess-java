package com.bjike.goddess.projectmarketfee.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 预警设计业务传输对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-09 04:53 ]
* @Description:	[ 预警设计业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WarnBO extends BaseBO { 

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