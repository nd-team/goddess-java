package com.bjike.goddess.democraticmeet.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 会议计划参与人员
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-02 11:29 ]
* @Description:	[ 会议计划参与人员 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AttenderTO extends BaseTO { 

/**
* 计划参与人员
*/
 private String  attenderName; 

/**
* 创建时间
*/
 private String  createTime; 

/**
* 修改时间
*/
 private String  modifyTime; 



 public String getAttenderName () { 
 return attenderName;
 } 
 public void setAttenderName (String attenderName ) { 
 this.attenderName = attenderName ; 
 } 
 public String getCreateTime () { 
 return createTime;
 } 
 public void setCreateTime (String createTime ) { 
 this.createTime = createTime ; 
 } 
 public String getModifyTime () { 
 return modifyTime;
 } 
 public void setModifyTime (String modifyTime ) { 
 this.modifyTime = modifyTime ; 
 } 
 }