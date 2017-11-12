package com.bjike.goddess.employeecontract.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 各类交流沟通模板
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-11 04:28 ]
* @Description:	[ 各类交流沟通模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "employeecontract_communicationtemplate")
public class CommunicationTemplate extends BaseEntity { 

/**
* 模板名称
*/
@Column(name = "templateName",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '模板名称'"  ) 
 private String  templateName; 

/**
* 所属类型
*/
@Column(name = "category",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '所属类型'"  ) 
 private String  category; 

/**
* 分类
*/
@Column(name = "classification",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '分类'"  ) 
 private String  classification; 

/**
* 触发字段
*/
@Column(name = "TriggerField",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '触发字段'"  ) 
 private String  TriggerField; 

/**
* 标题
*/
@Column(name = "title",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '标题'"  ) 
 private String  title; 

/**
* 邮件内容模板
*/
@Column(name = "emailContentTemplate",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '邮件内容模板'"  ) 
 private String  emailContentTemplate; 

/**
* 例子
*/
@Column(name = "example",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '例子'"  ) 
 private String  example; 

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
@Column(name = "copyObject",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '抄送对象'"  ) 
 private String  copyObject; 

/**
* 途径
*/
@Column(name = "path",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '途径'"  ) 
 private String  path; 



 public String getTemplateName () { 
 return templateName;
 } 
 public void setTemplateName (String templateName ) { 
 this.templateName = templateName ; 
 } 
 public String getCategory () { 
 return category;
 } 
 public void setCategory (String category ) { 
 this.category = category ; 
 } 
 public String getClassification () { 
 return classification;
 } 
 public void setClassification (String classification ) { 
 this.classification = classification ; 
 } 
 public String getTriggerField () { 
 return TriggerField;
 } 
 public void setTriggerField (String TriggerField ) { 
 this.TriggerField = TriggerField ; 
 } 
 public String getTitle () { 
 return title;
 } 
 public void setTitle (String title ) { 
 this.title = title ; 
 } 
 public String getEmailContentTemplate () { 
 return emailContentTemplate;
 } 
 public void setEmailContentTemplate (String emailContentTemplate ) { 
 this.emailContentTemplate = emailContentTemplate ; 
 } 
 public String getExample () { 
 return example;
 } 
 public void setExample (String example ) { 
 this.example = example ; 
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
 public String getCopyObject () { 
 return copyObject;
 } 
 public void setCopyObject (String copyObject ) { 
 this.copyObject = copyObject ; 
 } 
 public String getPath () { 
 return path;
 } 
 public void setPath (String path ) { 
 this.path = path ; 
 } 
 }