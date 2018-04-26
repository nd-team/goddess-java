package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 银行流水业务传输对象
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 10:29 ]
* @Description:	[ 银行流水业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankOfTheWaterDetailBO extends BaseBO { 

/**
* 银行流水
*/
 private BankOfTheWater  bankofthewater; 

/**
* 标题
*/
 private String  title; 

/**
* 内容
*/
 private String  val; 

/**
* 标题下标
*/
 private String  titleIndex; 



 public BankOfTheWater getBankofthewater () { 
 return bankofthewater;
 } 
 public void setBankofthewater (BankOfTheWater bankofthewater ) { 
 this.bankofthewater = bankofthewater ; 
 } 
 public String getTitle () { 
 return title;
 } 
 public void setTitle (String title ) { 
 this.title = title ; 
 } 
 public String getVal () { 
 return val;
 } 
 public void setVal (String val ) { 
 this.val = val ; 
 } 
 public String getTitleIndex () { 
 return titleIndex;
 } 
 public void setTitleIndex (String titleIndex ) { 
 this.titleIndex = titleIndex ; 
 } 
 }