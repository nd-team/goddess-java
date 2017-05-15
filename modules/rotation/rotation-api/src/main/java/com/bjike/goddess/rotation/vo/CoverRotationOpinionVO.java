package com.bjike.goddess.rotation.vo;

/**
* 岗位轮换自荐意见表现层对象
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-13 04:04 ]
* @Description:	[ 岗位轮换自荐意见表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CoverRotationOpinionVO { 

/**
* id
*/
 private String  id; 
/**
* 岗位轮换自荐
*/
 private CoverRotation  cover; 

/**
* 姓名
*/
 private String  username; 

/**
* 地区
*/
 private String  area; 

/**
* 项目组部门
*/
 private String  department; 

/**
* 岗位
*/
 private String  position; 

/**
* 意见
*/
 private String  opinion; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public CoverRotation getCover () { 
 return cover;
 } 
 public void setCover (CoverRotation cover ) { 
 this.cover = cover ; 
 } 
 public String getUsername () { 
 return username;
 } 
 public void setUsername (String username ) { 
 this.username = username ; 
 } 
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
 public String getOpinion () { 
 return opinion;
 } 
 public void setOpinion (String opinion ) { 
 this.opinion = opinion ; 
 } 
 }