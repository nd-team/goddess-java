package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 周计划的周期业务传输对象
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:37 ]
* @Description:	[ 周计划的周期业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class WeekCycleBO extends BaseBO { 

/**
* 业务方向科目id
*/
 private String  subjectDataId; 

/**
* 周期
*/
 private String  cycle; 

/**
* 各周工作量在整月占比
*/
 private String  radio; 



 public String getSubjectDataId () { 
 return subjectDataId;
 } 
 public void setSubjectDataId (String subjectDataId ) { 
 this.subjectDataId = subjectDataId ; 
 } 
 public String getCycle () { 
 return cycle;
 } 
 public void setCycle (String cycle ) { 
 this.cycle = cycle ; 
 } 
 public String getRadio () { 
 return radio;
 } 
 public void setRadio (String radio ) { 
 this.radio = radio ; 
 } 
 }