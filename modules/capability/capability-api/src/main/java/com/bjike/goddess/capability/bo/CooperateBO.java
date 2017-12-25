package com.bjike.goddess.capability.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 公司合作对象业务传输对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:03 ]
* @Description:	[ 公司合作对象业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CooperateBO extends BaseBO { 

/**
* 主表Id
*/
 private String  baseId; 

/**
* 名字
*/
 private String  name; 



 public String getBaseId () { 
 return baseId;
 } 
 public void setBaseId (String baseId ) { 
 this.baseId = baseId ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 }