package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 一层因素层权重业务传输对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-01 11:28 ]
* @Description:	[ 一层因素层权重业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class FirstFactorWeightBO extends BaseBO { 

/**
* 一级因素层因素名
*/
 private String  firstFactorName; 

/**
* 一级因素层因素权重
*/
 private Double  firstFactorWeight; 



 public String getFirstFactorName () { 
 return firstFactorName;
 } 
 public void setFirstFactorName (String firstFactorName ) { 
 this.firstFactorName = firstFactorName ; 
 } 
 public Double getFirstFactorWeight () { 
 return firstFactorWeight;
 } 
 public void setFirstFactorWeight (Double firstFactorWeight ) { 
 this.firstFactorWeight = firstFactorWeight ; 
 } 
 }