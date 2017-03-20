package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 获奖情况业务传输对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-20T10:53:15.050 ]
* @Description:	[ 获奖情况业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class RewardSituationBO extends BaseBO { 

/**
* 供应商基本信息
*/
 private SupplierInformation  information; 

/**
* 获奖名称
*/
 private String  name; 

/**
* 获得时间
*/
 private LocalDate  acquisition; 



 public SupplierInformation getInformation () { 
 return information;
 } 
 public void setInformation (SupplierInformation information ) { 
 this.information = information ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public LocalDate getAcquisition () { 
 return acquisition;
 } 
 public void setAcquisition (LocalDate acquisition ) { 
 this.acquisition = acquisition ; 
 } 
 }