package com.bjike.goddess.materialbuy.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 银行流水
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 10:29 ]
* @Description:	[ 银行流水 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "materialbuy_bankofthewaterdetail")
public class BankOfTheWaterDetail extends BaseEntity { 

/**
* 银行流水
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '银行流水'"  ) 
 private BankOfTheWater  bankofthewater; 

/**
* 标题
*/
@Column(name = "title",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '标题'"  ) 
 private String  title; 

/**
* 内容
*/
@Column(name = "val",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '内容'"  ) 
 private String  val; 

/**
* 标题下标
*/
@Column(name = "titleIndex",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '标题下标'"  ) 
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