package com.bjike.goddess.workhandover.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 工作交接责任与义务
* @Author:			[ chenyang ]
* @Date:			[  2017-11-13 06:26 ]
* @Description:	[ 工作交接责任与义务 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "workhandover_obligations")
public class Obligations extends BaseEntity { 

/**
* 对象
*/
@Column(name = "objects",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '对象'"  ) 
 private String  objects; 

/**
* 责任与义务
*/
@Column(name = "responsibilitiesAndObligations",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '责任与义务'"  ) 
 private String  responsibilitiesAndObligations; 



 public String getObjects () { 
 return objects;
 } 
 public void setObjects (String objects ) { 
 this.objects = objects ; 
 } 
 public String getResponsibilitiesAndObligations () { 
 return responsibilitiesAndObligations;
 } 
 public void setResponsibilitiesAndObligations (String responsibilitiesAndObligations ) { 
 this.responsibilitiesAndObligations = responsibilitiesAndObligations ; 
 } 
 }