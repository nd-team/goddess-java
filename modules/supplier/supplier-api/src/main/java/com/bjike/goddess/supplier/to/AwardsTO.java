package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
* 获奖情况
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:00 ]
* @Description:	[ 获奖情况 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class AwardsTO extends BaseTO { 

/**
* 奖励名称
*/
 private String  rewardName; 

/**
* 获得时间
*/
 private LocalDate forTime;



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