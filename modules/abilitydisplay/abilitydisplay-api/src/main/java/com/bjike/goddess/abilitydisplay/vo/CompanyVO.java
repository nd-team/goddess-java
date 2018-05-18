package com.bjike.goddess.abilitydisplay.vo;

import com.bjike.goddess.abilitydisplay.entity.*;

/**
* 公司表现层对象
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 03:38 ]
* @Description:	[ 公司表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CompanyVO { 

/**
* id
*/
 private String  id; 
/**
* 用途类型String
*/
 private String  type; 

/**
* 公司英文名称
*/
 private String  nameEn; 

/**
* 官网
*/
 private String  officialWeb; 

/**
* 地区
*/
 private String  area; 

/**
* 公司地址
*/
 private String  address; 

/**
* 注册地
*/
 private String  placeREG; 

/**
* 注册资金
*/
 private Double  capitalREG; 

/**
* 组成人员
*/
 private Personnel personnel;

/**
* 设备
*/
 private String  equipment; 

/**
* 公司规划
*/
 private CompanyPlan plan;

/**
* 公司认证
*/
 private ComCertificate auth;

/**
* 公司业务
*/
 private CompanyBN business;

/**
* 公司文化
*/
 private ComCultural cultural;

/**
* 公司联系人
*/
 private CompanyCon  contacts; 



 public String getId () { 
 return id;
 } 
 public void setId (String id ) { 
 this.id = id ; 
 } 
 public String getType () { 
 return type;
 } 
 public void setType (String type ) { 
 this.type = type ; 
 } 
 public String getNameEn () { 
 return nameEn;
 } 
 public void setNameEn (String nameEn ) { 
 this.nameEn = nameEn ; 
 } 
 public String getOfficialWeb () { 
 return officialWeb;
 } 
 public void setOfficialWeb (String officialWeb ) { 
 this.officialWeb = officialWeb ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getAddress () { 
 return address;
 } 
 public void setAddress (String address ) { 
 this.address = address ; 
 } 
 public String getPlaceREG () { 
 return placeREG;
 } 
 public void setPlaceREG (String placeREG ) { 
 this.placeREG = placeREG ; 
 } 
 public Double getCapitalREG () { 
 return capitalREG;
 } 
 public void setCapitalREG (Double capitalREG ) { 
 this.capitalREG = capitalREG ; 
 } 
 public Personnel getPersonnel () { 
 return personnel;
 } 
 public void setPersonnel (Personnel personnel ) { 
 this.personnel = personnel ; 
 } 
 public String getEquipment () { 
 return equipment;
 } 
 public void setEquipment (String equipment ) { 
 this.equipment = equipment ; 
 } 
 public CompanyPlan getPlan () { 
 return plan;
 } 
 public void setPlan (CompanyPlan plan ) { 
 this.plan = plan ; 
 } 
 public ComCertificate getAuth () { 
 return auth;
 } 
 public void setAuth (ComCertificate auth ) { 
 this.auth = auth ; 
 } 
 public CompanyBN getBusiness () { 
 return business;
 } 
 public void setBusiness (CompanyBN business ) { 
 this.business = business ; 
 } 
 public ComCultural getCultural () { 
 return cultural;
 } 
 public void setCultural (ComCultural cultural ) { 
 this.cultural = cultural ; 
 } 
 public CompanyCon getContacts () { 
 return contacts;
 } 
 public void setContacts (CompanyCon contacts ) { 
 this.contacts = contacts ; 
 } 
 }