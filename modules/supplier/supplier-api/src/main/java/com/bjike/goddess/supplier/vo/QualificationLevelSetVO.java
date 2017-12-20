package com.bjike.goddess.supplier.vo;

/**
* 资质等级设置表现层对象
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:08 ]
* @Description:	[ 资质等级设置表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class QualificationLevelSetVO { 

/**
* id
*/
 private String  id; 
/**
* 资质等级
*/
 private String  qualificationLevel; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getQualificationLevel () { 
 return qualificationLevel;
 } 
 public void setQualificationLevel (String qualificationLevel ) { 
 this.qualificationLevel = qualificationLevel ; 
 } 
 }