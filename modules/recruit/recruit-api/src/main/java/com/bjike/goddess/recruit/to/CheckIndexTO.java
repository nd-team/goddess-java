package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.recruit.entity.LabelOG;

/**
* 考核指标
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:26 ]
* @Description:	[ 考核指标 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CheckIndexTO extends BaseTO { 

/**
* 考核名称
*/
 private String  CheckName; 

/**
* 对赌标签
*/
 private LabelOG labelOG;



 public String getCheckName () { 
 return CheckName;
 } 
 public void setCheckName (String CheckName ) { 
 this.CheckName = CheckName ; 
 } 
 public LabelOG getLabelOG () { 
 return labelOG;
 } 
 public void setLabelOG (LabelOG labelOG ) { 
 this.labelOG = labelOG ; 
 } 
 }