package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 成本明细
* @Author:			[ wanyi ]
* @Date:			[  2017-12-25 04:09 ]
* @Description:	[ 成本明细 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CostDetailTO extends BaseTO { 

/**
* 分类
*/
 private String  type; 

/**
* 合计
*/
 private Double  total; 

/**
* 第十日
*/
 private Double  tenthDay; 

/**
* 第十五日
*/
 private Double  fifteenthDay; 

/**
* 第二十日
*/
 private Double  twentiethDay; 

/**
* 第三十日
*/
 private Double  thirtiethDay; 



 public String getType () { 
 return type;
 } 
 public void setType (String type ) { 
 this.type = type ; 
 } 
 public Double getTotal () { 
 return total;
 } 
 public void setTotal (Double total ) { 
 this.total = total ; 
 } 
 public Double getTenthDay () { 
 return tenthDay;
 } 
 public void setTenthDay (Double tenthDay ) { 
 this.tenthDay = tenthDay ; 
 } 
 public Double getFifteenthDay () { 
 return fifteenthDay;
 } 
 public void setFifteenthDay (Double fifteenthDay ) { 
 this.fifteenthDay = fifteenthDay ; 
 } 
 public Double getTwentiethDay () { 
 return twentiethDay;
 } 
 public void setTwentiethDay (Double twentiethDay ) { 
 this.twentiethDay = twentiethDay ; 
 } 
 public Double getThirtiethDay () { 
 return thirtiethDay;
 } 
 public void setThirtiethDay (Double thirtiethDay ) { 
 this.thirtiethDay = thirtiethDay ; 
 } 
 }