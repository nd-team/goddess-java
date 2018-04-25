package com.bjike.goddess.materialbuy.vo;

/**
* 银行流水表现层对象
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 10:29 ]
* @Description:	[ 银行流水表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankOfTheWaterDetailVO { 

/**
* id
*/
 private String  id; 
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



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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