package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 资质等级设置
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:08 ]
* @Description:	[ 资质等级设置 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "supplier_qualificationlevelset")
public class QualificationLevelSet extends BaseEntity { 

/**
* 资质等级
*/
@Column(name = "qualificationLevel",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '资质等级'"  ) 
 private String  qualificationLevel; 



 public String getQualificationLevel () { 
 return qualificationLevel;
 } 
 public void setQualificationLevel (String qualificationLevel ) { 
 this.qualificationLevel = qualificationLevel ; 
 } 
 }