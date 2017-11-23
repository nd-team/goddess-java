package com.bjike.goddess.projectprocing.vo;

/**
* 通报机制制定表现层对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:24 ]
* @Description:	[ 通报机制制定表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class NotificationFormulaVO { 

/**
* id
*/
 private String  id; 
/**
* 汇总表名
*/
 private SummTableName  summTableName; 

/**
* 创建/修改人
*/
 private String  modifier; 

/**
* 发送频率
*/
 private SendFrequency  sendFrequency; 

/**
* 发送时间节点
*/
 private LocalDate  sendTimeNode; 

/**
* 发送对象
*/
 private String  sendObject; 

/**
* 是否发送项目组/部门全部人
*/
 private Boolean  sendSectoral; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
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