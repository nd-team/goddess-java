package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 获奖情况业务传输对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:00 ]
* @Description:	[ 获奖情况业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AwardsBO extends BaseBO { 

/**
* 奖励名称
*/
 private String  rewardName; 

/**
* 获得时间
*/
 private LocalDate  forTime; 



 public String getRewardName () { 
 return rewardName;
 } 
 public void setRewardName (String rewardName ) { 
 this.rewardName = rewardName ; 
 } 
 public LocalDate getForTime () {
 return forTime;
 } 
 public void setForTime (LocalDate forTime ) { 
 this.forTime = forTime ; 
 } 
 }