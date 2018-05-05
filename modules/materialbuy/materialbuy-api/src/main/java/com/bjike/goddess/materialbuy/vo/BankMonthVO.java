package com.bjike.goddess.materialbuy.vo;

import java.time.LocalDate;

/**
* 银行流水表现层对象
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 04:29 ]
* @Description:	[ 银行流水表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankMonthVO { 

/**
* id
*/
 private String  id; 
/**
* month
*/
 private LocalDate shijian;



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 }

 public LocalDate getShijian() {
  return shijian;
 }

 public void setShijian(LocalDate shijian) {
  this.shijian = shijian;
 }
}