package com.bjike.goddess.materialbuy.vo;

import java.time.LocalDate;

/**
* 对银行流水汇总表现层对象
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-02 02:21 ]
* @Description:	[ 对银行流水汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankSummaryVO { 

/**
* id
*/
 private String  id; 
/**
* 银行名称
*/
 private String  bankName; 

/**
* 日期；Long
*/
 private LocalDate theDateOf;

/**
* 本月发生额
*/
 private Long  amountOfThisMonth; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getBankName () { 
 return bankName;
 } 
 public void setBankName (String bankName ) { 
 this.bankName = bankName ; 
 } 
 public LocalDate getTheDateOf () { 
 return theDateOf;
 } 
 public void setTheDateOf (LocalDate theDateOf ) { 
 this.theDateOf = theDateOf ; 
 } 
 public Long getAmountOfThisMonth () { 
 return amountOfThisMonth;
 } 
 public void setAmountOfThisMonth (Long amountOfThisMonth ) { 
 this.amountOfThisMonth = amountOfThisMonth ; 
 } 
 }