package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectprocing.entity.SendFrequency;
import com.bjike.goddess.projectprocing.enums.SummTableName;

import java.time.LocalDate;

/**
* 通报机制制定
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:24 ]
* @Description:	[ 通报机制制定 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class NotificationFormulaTO extends BaseTO { 

/**
* 汇总表名
*/
 private SummTableName summTableName;

/**
* 创建/修改人
*/
 private String  modifier; 

/**
* 发送频率
*/
 private SendFrequency sendFrequency;

/**
* 发送时间节点
*/
 private LocalDate sendTimeNode;

/**
* 发送对象
*/
 private String  sendObject; 

/**
* 是否发送项目组/部门全部人
*/
 private Boolean  sendSectoral; 



 public SummTableName getSummTableName () { 
 return summTableName;
 } 
 public void setSummTableName (SummTableName summTableName ) { 
 this.summTableName = summTableName ; 
 } 
 public String getModifier () { 
 return modifier;
 } 
 public void setModifier (String modifier ) { 
 this.modifier = modifier ; 
 } 
 public SendFrequency getSendFrequency () { 
 return sendFrequency;
 } 
 public void setSendFrequency (SendFrequency sendFrequency ) { 
 this.sendFrequency = sendFrequency ; 
 } 
 public LocalDate getSendTimeNode () { 
 return sendTimeNode;
 } 
 public void setSendTimeNode (LocalDate sendTimeNode ) { 
 this.sendTimeNode = sendTimeNode ; 
 } 
 public String getSendObject () { 
 return sendObject;
 } 
 public void setSendObject (String sendObject ) { 
 this.sendObject = sendObject ; 
 } 
 public Boolean getSendSectoral () { 
 return sendSectoral;
 } 
 public void setSendSectoral (Boolean sendSectoral ) { 
 this.sendSectoral = sendSectoral ; 
 } 
 }