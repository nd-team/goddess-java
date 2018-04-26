package com.bjike.goddess.bankrecords.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 银行流水
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-08 09:53 ]
* @Description:	[ 银行流水 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankAccountInformationTO extends BaseTO {

 /**
  * 名称
  */

 private String theUser;

 /**
  * 银行账户；
  */

 private String theBankAccount;

 /**
  * 备注
  */

 private String note;

 /**
  * 开户行
  */

 private String whereItIs;

 /**
  * 开户行属性
  */

 private String bankProperty;

 /**
  * 开户行地址
  */

 private String bankAddress;

 public String getWhereItIs() {
  return whereItIs;
 }

 public void setWhereItIs(String whereItIs) {
  this.whereItIs = whereItIs;
 }

 public String getBankProperty() {
  return bankProperty;
 }

 public void setBankProperty(String bankProperty) {
  this.bankProperty = bankProperty;
 }

 public String getBankAddress() {
  return bankAddress;
 }

 public void setBankAddress(String bankAddress) {
  this.bankAddress = bankAddress;
 }

 public String getTheUser() {
  return theUser;
 }

 public void setTheUser(String theUser) {
  this.theUser = theUser;
 }

 public String getTheBankAccount() {
  return theBankAccount;
 }

 public void setTheBankAccount(String theBankAccount) {
  this.theBankAccount = theBankAccount;
 }

 public String getNote() {
  return note;
 }

 public void setNote(String note) {
  this.note = note;
 }
}