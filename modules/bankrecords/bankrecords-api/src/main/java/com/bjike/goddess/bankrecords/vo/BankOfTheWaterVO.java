package com.bjike.goddess.bankrecords.vo;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 银行流水表现层对象
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-08 10:21 ]
* @Description:	[ 银行流水表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankOfTheWaterVO {
 /**
  * 交易日
  */
 private LocalDate tradingDay;

 /**
  * 交易时间 ；LocalDate
  */
 private LocalDate theBankAccount;

 /**
  * 贷方金额
  */
 private Double theSum;

 /**
  * 余额
  */
 private Double theBalanceOf;

 /**
  * 摘要
  */
 private String abstracts;

 /**
  * 银行名称
  */
 private String bankName;

 public String getBankName() {
  return bankName;
 }

 public void setBankName(String bankName) {
  this.bankName = bankName;
 }

 public LocalDate getTradingDay() {
  return tradingDay;
 }

 public void setTradingDay(LocalDate tradingDay) {
  this.tradingDay = tradingDay;
 }

 public LocalDate getTheBankAccount() {
  return theBankAccount;
 }

 public void setTheBankAccount(LocalDate theBankAccount) {
  this.theBankAccount = theBankAccount;
 }

 public Double getTheSum() {
  return theSum;
 }

 public void setTheSum(Double theSum) {
  this.theSum = theSum;
 }

 public Double getTheBalanceOf() {
  return theBalanceOf;
 }

 public void setTheBalanceOf(Double theBalanceOf) {
  this.theBalanceOf = theBalanceOf;
 }

 public String getAbstracts() {
  return abstracts;
 }

 public void setAbstracts(String abstracts) {
  this.abstracts = abstracts;
 }
 }