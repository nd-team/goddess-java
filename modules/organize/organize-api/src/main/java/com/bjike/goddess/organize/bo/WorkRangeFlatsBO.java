package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 工作范围信息设置平台业务传输对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-06 10:58 ]
* @Description:	[ 工作范围信息设置平台业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WorkRangeFlatsBO extends BaseBO { 

/**
* faltId
*/
 private String  faltId; 



 public String getFaltId () { 
 return faltId;
 } 
 public void setFaltId (String faltId ) { 
 this.faltId = faltId ; 
 } 
 }