package com.bjike.goddess.contractware.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 合同管理图形化
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-03 02:20 ]
* @Description:	[ 合同管理图形化 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "contractware_imagecollectcontract")
public class ImageCollectContract extends BaseEntity { 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 



 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 }