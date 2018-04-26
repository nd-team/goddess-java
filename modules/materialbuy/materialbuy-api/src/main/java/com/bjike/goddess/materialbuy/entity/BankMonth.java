package com.bjike.goddess.materialbuy.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 银行流水
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 04:29 ]
* @Description:	[ 银行流水 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "materialbuy_bankmonth")
public class BankMonth extends BaseEntity { 

/**
* month
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT 'month'"  ) 
 private   LocalDate; 



 public  getLocalDate () { 
 return LocalDate;
 } 
 public void setLocalDate ( LocalDate ) { 
 this.LocalDate = LocalDate ; 
 } 
 }