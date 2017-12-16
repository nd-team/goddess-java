package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 资质等级设置
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:08 ]
* @Description:	[ 资质等级设置 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class QualificationLevelSetTO extends BaseTO { 

/**
* 资质等级
*/
 private String  qualificationLevel; 



 public String getQualificationLevel () { 
 return qualificationLevel;
 } 
 public void setQualificationLevel (String qualificationLevel ) { 
 this.qualificationLevel = qualificationLevel ; 
 } 
 }