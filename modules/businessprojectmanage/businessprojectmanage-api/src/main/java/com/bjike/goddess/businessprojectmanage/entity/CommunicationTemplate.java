package com.bjike.goddess.businessprojectmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 各类沟通交流模板
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-09 02:31 ]
* @Description:	[ 各类沟通交流模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "businessprojectmanage_communicationtemplate")
public class CommunicationTemplate extends BaseEntity { 

/**
* 模板编号
*/
@Column(name = "templateNumber",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '模板编号'"  ) 
 private String  templateNumber; 

/**
* 所属类型
*/
@Column(name = "belongType",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '所属类型'"  ) 
 private String  belongType; 

/**
* 分类
*/
@Column(name = "type",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '分类'"  ) 
 private String  type; 

/**
* 触发字段
*/
@Column(name = "triggerField",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '触发字段'"  ) 
 private String  triggerField; 

/**
* 标题
*/
@Column(name = "title",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '标题'"  ) 
 private String  title; 

/**
* 邮件内容模板
*/
@Column(name = "contentTemplate",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '邮件内容模板'"  ) 
 private String  contentTemplate; 

/**
* 例子
*/
@Column(name = "examples",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '例子'"  ) 
 private String  examples; 

/**
* 附件
*/
@Column(name = "attachment",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '附件'"  ) 
 private String  attachment; 

/**
* 主送对象
*/
@Column(name = "mainObject",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '主送对象'"  ) 
 private String  mainObject; 

/**
* 抄送对象
*/
@Column(name = "ccObject",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '抄送对象'"  ) 
 private String  ccObject; 

/**
* 途径
*/
@Column(name = "way",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '途径'"  ) 
 private String  way; 



 public String getTemplateNumber () { 
 return templateNumber;
 } 
 public void setTemplateNumber (String templateNumber ) { 
 this.templateNumber = templateNumber ; 
 } 
 public String getBelongType () { 
 return belongType;
 } 
 public void setBelongType (String belongType ) { 
 this.belongType = belongType ; 
 } 
 public String getType () { 
 return type;
 } 
 public void setType (String type ) { 
 this.type = type ; 
 } 
 public String getTriggerField () { 
 return triggerField;
 } 
 public void setTriggerField (String triggerField ) { 
 this.triggerField = triggerField ; 
 } 
 public String getTitle () { 
 return title;
 } 
 public void setTitle (String title ) { 
 this.title = title ; 
 } 
 public String getContentTemplate () { 
 return contentTemplate;
 } 
 public void setContentTemplate (String contentTemplate ) { 
 this.contentTemplate = contentTemplate ; 
 } 
 public String getExamples () { 
 return examples;
 } 
 public void setExamples (String examples ) { 
 this.examples = examples ; 
 } 
 public String getAttachment () { 
 return attachment;
 } 
 public void setAttachment (String attachment ) { 
 this.attachment = attachment ; 
 } 
 public String getMainObject () { 
 return mainObject;
 } 
 public void setMainObject (String mainObject ) { 
 this.mainObject = mainObject ; 
 } 
 public String getCcObject () { 
 return ccObject;
 } 
 public void setCcObject (String ccObject ) { 
 this.ccObject = ccObject ; 
 } 
 public String getWay () { 
 return way;
 } 
 public void setWay (String way ) { 
 this.way = way ; 
 } 
 }