package com.bjike.goddess.materialbuy.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
* 银行流水
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 04:29 ]
* @Description:	[ 银行流水 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankMonthTO extends BaseTO { 

/**
* month
*/
 private LocalDate shijian;


 public LocalDate getShijian() {
  return shijian;
 }

 public void setShijian(LocalDate shijian) {
  this.shijian = shijian;
 }
}