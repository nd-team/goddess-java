package com.bjike.goddess.rentutilitiespay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "rentutilitiespay_collect")
public class Collect extends BaseEntity { 

/**
* 地区
*/
@Column(name = "area",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '地区'"  ) 
 private String  area; 

/**
* 项目组/部门
*/
@Column(name = "projectGroup",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'"  ) 
 private String  projectGroup; 

/**
* 租房地址
*/
@Column(name = "address",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '租房地址'"  ) 
 private String  address; 

/**
* 房租缴付总金额
*/
@Column(name = "rent",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '房租缴付总金额'"  ) 
 private Double  rent; 

/**
* 水费缴付总金额
*/
@Column(name = "waterPayMoney",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '水费缴付总金额'"  ) 
 private Double  waterPayMoney; 

/**
* 电费缴付总金额
*/
@Column(name = "energyPayMoney",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '电费缴付总金额'"  ) 
 private Double  energyPayMoney; 

/**
* 燃气费缴付总金额
*/
@Column(name = "gasRechargeLines",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '燃气费缴付总金额'"  ) 
 private Double  gasRechargeLines; 

/**
* 管理费、卫生费总金额
*/
@Column(name = "fee",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '管理费、卫生费总金额'"  ) 
 private Double  fee; 

/**
* 平摊水费金额
*/
@Column(name = "waterStaffPay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '平摊水费金额'"  ) 
 private Double  waterStaffPay; 

/**
* 平摊电费金额
*/
@Column(name = "energyStaffPay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '平摊电费金额'"  ) 
 private Double  energyStaffPay; 

/**
* 平摊燃气费金额
*/
@Column(name = "gasStaffPay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '平摊燃气费金额'"  ) 
 private Double  gasStaffPay; 

/**
* 预缴费用金额
*/
@Column(name = "waterStaffPrepay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '预缴费用金额'"  ) 
 private Double  waterStaffPrepay; 

/**
* 预缴水费金额
*/
@Column(name = "energyStaffPrepay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '预缴水费金额'"  ) 
 private Double  energyStaffPrepay; 

/**
* 预缴燃气费金额
*/
@Column(name = "gasStaffPrepay",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '预缴燃气费金额'"  ) 
 private Double  gasStaffPrepay; 

/**
* 员工应缴金额
*/
@Column(name = "staffPayCollect",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '员工应缴金额'"  ) 
 private Double  staffPayCollect; 

/**
* 员工核实确认数
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '员工核实确认数'"  ) 
 private Integer  staffVerifySureNumber; 

/**
* 员工核实有误数
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '员工核实有误数'"  ) 
 private Integer  staffVerifyWrongNumber; 



 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getProjectGroup () { 
 return projectGroup;
 } 
 public void setProjectGroup (String projectGroup ) { 
 this.projectGroup = projectGroup ; 
 } 
 public String getAddress () { 
 return address;
 } 
 public void setAddress (String address ) { 
 this.address = address ; 
 } 
 public Double getRent () { 
 return rent;
 } 
 public void setRent (Double rent ) { 
 this.rent = rent ; 
 } 
 public Double getWaterPayMoney () { 
 return waterPayMoney;
 } 
 public void setWaterPayMoney (Double waterPayMoney ) { 
 this.waterPayMoney = waterPayMoney ; 
 } 
 public Double getEnergyPayMoney () { 
 return energyPayMoney;
 } 
 public void setEnergyPayMoney (Double energyPayMoney ) { 
 this.energyPayMoney = energyPayMoney ; 
 } 
 public Double getGasRechargeLines () { 
 return gasRechargeLines;
 } 
 public void setGasRechargeLines (Double gasRechargeLines ) { 
 this.gasRechargeLines = gasRechargeLines ; 
 } 
 public Double getFee () { 
 return fee;
 } 
 public void setFee (Double fee ) { 
 this.fee = fee ; 
 } 
 public Double getWaterStaffPay () { 
 return waterStaffPay;
 } 
 public void setWaterStaffPay (Double waterStaffPay ) { 
 this.waterStaffPay = waterStaffPay ; 
 } 
 public Double getEnergyStaffPay () { 
 return energyStaffPay;
 } 
 public void setEnergyStaffPay (Double energyStaffPay ) { 
 this.energyStaffPay = energyStaffPay ; 
 } 
 public Double getGasStaffPay () { 
 return gasStaffPay;
 } 
 public void setGasStaffPay (Double gasStaffPay ) { 
 this.gasStaffPay = gasStaffPay ; 
 } 
 public Double getWaterStaffPrepay () { 
 return waterStaffPrepay;
 } 
 public void setWaterStaffPrepay (Double waterStaffPrepay ) { 
 this.waterStaffPrepay = waterStaffPrepay ; 
 } 
 public Double getEnergyStaffPrepay () { 
 return energyStaffPrepay;
 } 
 public void setEnergyStaffPrepay (Double energyStaffPrepay ) { 
 this.energyStaffPrepay = energyStaffPrepay ; 
 } 
 public Double getGasStaffPrepay () { 
 return gasStaffPrepay;
 } 
 public void setGasStaffPrepay (Double gasStaffPrepay ) { 
 this.gasStaffPrepay = gasStaffPrepay ; 
 } 
 public Double getStaffPayCollect () { 
 return staffPayCollect;
 } 
 public void setStaffPayCollect (Double staffPayCollect ) { 
 this.staffPayCollect = staffPayCollect ; 
 } 
 public Integer getStaffVerifySureNumber () { 
 return staffVerifySureNumber;
 } 
 public void setStaffVerifySureNumber (Integer staffVerifySureNumber ) { 
 this.staffVerifySureNumber = staffVerifySureNumber ; 
 } 
 public Integer getStaffVerifyWrongNumber () { 
 return staffVerifyWrongNumber;
 } 
 public void setStaffVerifyWrongNumber (Integer staffVerifyWrongNumber ) { 
 this.staffVerifyWrongNumber = staffVerifyWrongNumber ; 
 } 
 }