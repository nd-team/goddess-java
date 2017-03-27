package com.bjike.goddess.headcount.vo;

/**
* 部门人数配置(计划)表现层对象
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:18:34.783 ]
* @Description:	[ 部门人数配置(计划)表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ConfigPlanVO {

/**
* 分类
*/
 private String  category; 

/**
* 总权重
*/
 private Integer  weights; 

/**
* 部门
*/
 private String  department; 

/**
* 权重比例
*/
 private Double  weightings; 

/**
* 项目分类
*/
 private String  itemCategory; 

/**
* 决策层人数占比
*/
 private Double  olicyMakers; 

/**
* 决策层人数
*/
 private Integer  olicyMakersNum; 

/**
* 技术管理人数占比
*/
 private Double  technology; 

/**
* 技术管理层人数
*/
 private Integer  technologyNum; 

/**
* 行政管理层人数占比
*/
 private Double  administrative; 

/**
* 行政管理层人数
*/
 private Integer  administrativeNum; 

/**
* 执行层人数占比
*/
 private Double  perform; 

/**
* 执行层人数
*/
 private Integer  performNum; 

/**
* 编制总人数
*/
 private Integer  totalNum; 



 public String getCategory () { 
 return category;
 } 
 public void setCategory (String category ) { 
 this.category = category ; 
 } 
 public Integer getWeights () { 
 return weights;
 } 
 public void setWeights (Integer weights ) { 
 this.weights = weights ; 
 } 
 public String getDepartment () { 
 return department;
 } 
 public void setDepartment (String department ) { 
 this.department = department ; 
 } 
 public Double getWeightings () { 
 return weightings;
 } 
 public void setWeightings (Double weightings ) { 
 this.weightings = weightings ; 
 } 
 public String getItemCategory () { 
 return itemCategory;
 } 
 public void setItemCategory (String itemCategory ) { 
 this.itemCategory = itemCategory ; 
 } 
 public Double getOlicyMakers () { 
 return olicyMakers;
 } 
 public void setOlicyMakers (Double olicyMakers ) { 
 this.olicyMakers = olicyMakers ; 
 } 
 public Integer getOlicyMakersNum () { 
 return olicyMakersNum;
 } 
 public void setOlicyMakersNum (Integer olicyMakersNum ) { 
 this.olicyMakersNum = olicyMakersNum ; 
 } 
 public Double getTechnology () { 
 return technology;
 } 
 public void setTechnology (Double technology ) { 
 this.technology = technology ; 
 } 
 public Integer getTechnologyNum () { 
 return technologyNum;
 } 
 public void setTechnologyNum (Integer technologyNum ) { 
 this.technologyNum = technologyNum ; 
 } 
 public Double getAdministrative () { 
 return administrative;
 } 
 public void setAdministrative (Double administrative ) { 
 this.administrative = administrative ; 
 } 
 public Integer getAdministrativeNum () { 
 return administrativeNum;
 } 
 public void setAdministrativeNum (Integer administrativeNum ) { 
 this.administrativeNum = administrativeNum ; 
 } 
 public Double getPerform () { 
 return perform;
 } 
 public void setPerform (Double perform ) { 
 this.perform = perform ; 
 } 
 public Integer getPerformNum () { 
 return performNum;
 } 
 public void setPerformNum (Integer performNum ) { 
 this.performNum = performNum ; 
 } 
 public Integer getTotalNum () { 
 return totalNum;
 } 
 public void setTotalNum (Integer totalNum ) { 
 this.totalNum = totalNum ; 
 } 
 }