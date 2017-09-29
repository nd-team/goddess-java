package com.bjike.goddess.dimission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 离职办理节点情况
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-28 11:28 ]
* @Description:	[ 离职办理节点情况 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "dimission_situation")
public class Situation extends BaseEntity { 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 

/**
* 项目组/部门
*/
@Column(name = "department",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'"  ) 
 private String  department; 

/**
* 岗位
*/
@Column(name = "position",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位'"  ) 
 private String  position; 

/**
* 岗位层级
*/
@Column(name = "positionLever",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'"  ) 
 private String  positionLever; 

/**
* 姓名
*/
@Column(name = "name",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '姓名'"  ) 
 private String  name; 

/**
* 是否填写离职申请表
*/
@Column(name = "is_isBuild",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否填写离职申请表'"  , insertable = false  ) 
 private Boolean  isBuild; 

/**
* 是否待离职
*/
@Column(name = "is_buildTime",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否待离职'"  , insertable = false  ) 
 private Boolean  buildTime; 

/**
* 是否已离职
*/
@Column(name = "is_isComplete",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已离职'"  , insertable = false  ) 
 private Boolean  isComplete; 

/**
* 是否正常离职
*/
@Column(name = "is_aimAmount",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否正常离职'"  , insertable = false  ) 
 private Boolean  aimAmount; 

/**
* 是否自离
*/
@Column(name = "is_planAmount",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否自离'"  , insertable = false  ) 
 private Boolean  planAmount; 

/**
* 是否辞退
*/
@Column(name = "is_actualAmount",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否辞退'"  , insertable = false  ) 
 private Boolean  actualAmount; 

/**
* 是否冻结账号
*/
@Column(name = "is_informationProvide",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否冻结账号'"  , insertable = false  ) 
 private Boolean  informationProvide; 

/**
* 是否可挽留
*/
@Column(name = "is_messageProportion",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可挽留'"  , insertable = false  ) 
 private Boolean  messageProportion; 

/**
* 是否工作交接数
*/
@Column(name = "is_provideAccount",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否工作交接数'"  , insertable = false  ) 
 private Boolean  provideAccount; 

/**
* 是否可提前离职
*/
@Column(name = "is_businessContracting",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可提前离职'"  , insertable = false  ) 
 private Boolean  businessContracting; 

/**
* 是否需要延后离职
*/
@Column(name = "is_businessProportion",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需要延后离职'"  , insertable = false  ) 
 private Boolean  businessProportion; 

/**
* 是否需填写《员工离职工作交接手续表》
*/
@Column(name = "is_contractAccount",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需填写《员工离职工作交接手续表》'"  , insertable = false  ) 
 private Boolean  contractAccount; 

/**
* 项目经理需审核是否可提前离职
*/
@Column(name = "is_businessNegotiation",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '项目经理需审核是否可提前离职'"  , insertable = false  ) 
 private Boolean  businessNegotiation; 

/**
* 模块负责人需审核是否可提前离职
*/
@Column(name = "is_talkProportion",nullable = false,columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '模块负责人需审核是否可提前离职'"  , insertable = false  ) 
 private Boolean  talkProportion; 



 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getDepartment () { 
 return department;
 } 
 public void setDepartment (String department ) { 
 this.department = department ; 
 } 
 public String getPosition () { 
 return position;
 } 
 public void setPosition (String position ) { 
 this.position = position ; 
 } 
 public String getPositionLever () { 
 return positionLever;
 } 
 public void setPositionLever (String positionLever ) { 
 this.positionLever = positionLever ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public Boolean getIsBuild () { 
 return isBuild;
 } 
 public void setIsBuild (Boolean isBuild ) { 
 this.isBuild = isBuild ; 
 } 
 public Boolean getBuildTime () { 
 return buildTime;
 } 
 public void setBuildTime (Boolean buildTime ) { 
 this.buildTime = buildTime ; 
 } 
 public Boolean getIsComplete () { 
 return isComplete;
 } 
 public void setIsComplete (Boolean isComplete ) { 
 this.isComplete = isComplete ; 
 } 
 public Boolean getAimAmount () { 
 return aimAmount;
 } 
 public void setAimAmount (Boolean aimAmount ) { 
 this.aimAmount = aimAmount ; 
 } 
 public Boolean getPlanAmount () { 
 return planAmount;
 } 
 public void setPlanAmount (Boolean planAmount ) { 
 this.planAmount = planAmount ; 
 } 
 public Boolean getActualAmount () { 
 return actualAmount;
 } 
 public void setActualAmount (Boolean actualAmount ) { 
 this.actualAmount = actualAmount ; 
 } 
 public Boolean getInformationProvide () { 
 return informationProvide;
 } 
 public void setInformationProvide (Boolean informationProvide ) { 
 this.informationProvide = informationProvide ; 
 } 
 public Boolean getMessageProportion () { 
 return messageProportion;
 } 
 public void setMessageProportion (Boolean messageProportion ) { 
 this.messageProportion = messageProportion ; 
 } 
 public Boolean getProvideAccount () { 
 return provideAccount;
 } 
 public void setProvideAccount (Boolean provideAccount ) { 
 this.provideAccount = provideAccount ; 
 } 
 public Boolean getBusinessContracting () { 
 return businessContracting;
 } 
 public void setBusinessContracting (Boolean businessContracting ) { 
 this.businessContracting = businessContracting ; 
 } 
 public Boolean getBusinessProportion () { 
 return businessProportion;
 } 
 public void setBusinessProportion (Boolean businessProportion ) { 
 this.businessProportion = businessProportion ; 
 } 
 public Boolean getContractAccount () { 
 return contractAccount;
 } 
 public void setContractAccount (Boolean contractAccount ) { 
 this.contractAccount = contractAccount ; 
 } 
 public Boolean getBusinessNegotiation () { 
 return businessNegotiation;
 } 
 public void setBusinessNegotiation (Boolean businessNegotiation ) { 
 this.businessNegotiation = businessNegotiation ; 
 } 
 public Boolean getTalkProportion () { 
 return talkProportion;
 } 
 public void setTalkProportion (Boolean talkProportion ) { 
 this.talkProportion = talkProportion ; 
 } 
 }