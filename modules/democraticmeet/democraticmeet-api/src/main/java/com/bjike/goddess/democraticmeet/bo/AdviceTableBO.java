package com.bjike.goddess.democraticmeet.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 建议表业务传输对象
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-03 11:26 ]
* @Description:	[ 建议表业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AdviceTableBO extends BaseBO { 

/**
* 他人给予的建议
*/
 private String  advice; 

/**
* 建议人
*/
 private String  advicer; 

/**
* 创建时间
*/
 private String  createTime; 

/**
* 修改时间
*/
 private String  modifyTime; 



 public String getAdvice () { 
 return advice;
 } 
 public void setAdvice (String advice ) { 
 this.advice = advice ; 
 } 
 public String getAdvicer () { 
 return advicer;
 } 
 public void setAdvicer (String advicer ) { 
 this.advicer = advicer ; 
 } 
 public String getCreateTime () { 
 return createTime;
 } 
 public void setCreateTime (String createTime ) { 
 this.createTime = createTime ; 
 } 
 public String getModifyTime () { 
 return modifyTime;
 } 
 public void setModifyTime (String modifyTime ) { 
 this.modifyTime = modifyTime ; 
 } 
 }