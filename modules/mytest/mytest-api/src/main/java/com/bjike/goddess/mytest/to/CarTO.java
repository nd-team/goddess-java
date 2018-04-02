package com.bjike.goddess.mytest.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 测试用类 汽车
* @Author:			[ wanyi ]
* @Date:			[  2017-12-16 11:13 ]
* @Description:	[ 测试用类 汽车 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CarTO extends BaseTO { 

/**
* 速度
*/
 private String  speed; 

/**
* 颜色
*/
 private String  color; 

/**
* 品牌
*/
 private String  brand; 



 public String getSpeed () { 
 return speed;
 } 
 public void setSpeed (String speed ) { 
 this.speed = speed ; 
 } 
 public String getColor () { 
 return color;
 } 
 public void setColor (String color ) { 
 this.color = color ; 
 } 
 public String getBrand () { 
 return brand;
 } 
 public void setBrand (String brand ) { 
 this.brand = brand ; 
 } 
 }