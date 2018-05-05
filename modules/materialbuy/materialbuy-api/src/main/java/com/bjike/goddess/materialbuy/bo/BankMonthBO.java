package com.bjike.goddess.materialbuy.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 银行流水业务传输对象
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 04:29 ]
* @Description:	[ 银行流水业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankMonthBO extends BaseBO { 

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