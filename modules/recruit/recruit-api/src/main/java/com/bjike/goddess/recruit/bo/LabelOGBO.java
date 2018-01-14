package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 对赌标签业务传输对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:23 ]
* @Description:	[ 对赌标签业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class LabelOGBO extends BaseBO {

/**
* 标签名称
*/
 private String  labelName; 

/**
* 标签说明
*/
 private String  labelExplain; 



 public String getLabelName () { 
 return labelName;
 } 
 public void setLabelName (String labelName ) { 
 this.labelName = labelName ; 
 } 
 public String getLabelExplain () { 
 return labelExplain;
 } 
 public void setLabelExplain (String labelExplain ) { 
 this.labelExplain = labelExplain ; 
 } 
 }