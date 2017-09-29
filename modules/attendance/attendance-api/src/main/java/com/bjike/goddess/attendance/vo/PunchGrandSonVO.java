package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.attendance.enums.PunchStatus;

/**
* 打卡孙子表表现层对象
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-22 05:13 ]
* @Description:	[ 打卡孙子表表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PunchGrandSonVO { 

/**
* id
*/
 private String  id; 
/**
* 打卡状态
*/
 private PunchStatus punchStatus;

/**
* 父id
*/
 private String  punchSonId; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public PunchStatus getPunchStatus () { 
 return punchStatus;
 } 
 public void setPunchStatus (PunchStatus punchStatus ) { 
 this.punchStatus = punchStatus ; 
 } 
 public String getPunchSonId () { 
 return punchSonId;
 } 
 public void setPunchSonId (String punchSonId ) { 
 this.punchSonId = punchSonId ; 
 } 
 }