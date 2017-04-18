package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 交接信息参考
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-17 02:36 ]
* @Description:	[ 交接信息参考 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class HandoverReferenceTO extends BaseTO { 

/**
* 当前工作范围
*/
 private String  cycle; 

/**
* 当前工作目的
*/
 private String  purpose; 

/**
* 当前工作范围的权限
*/
 private String  authority; 

/**
* 当前工作资料
*/
 private String  material; 

/**
* 文件内容明细
*/
 private String  detail; 

/**
* 文件存储路径
*/
 private String  storage; 

/**
* 任务交接类型
*/
 private String  handoverType; 

/**
* 任务交接内容明细
*/
 private String  handoverDetail; 

/**
* 设备交接型号
*/
 private String  handoverModel; 

/**
* 设备交接内容
*/
 private String  handoverContent; 

/**
* 设备交接存储内容
*/
 private String  handoverStorage; 

/**
* 股权或期权交接类型
*/
 private String  stockType; 

/**
* 股权或期权交接内容
*/
 private String  stockContent; 

/**
* 办公用品交接类型
*/
 private String  suppliesType; 

/**
* 办公用品交接内容
*/
 private String  suppliesContent; 



 public String getCycle () { 
 return cycle;
 } 
 public void setCycle (String cycle ) { 
 this.cycle = cycle ; 
 } 
 public String getPurpose () { 
 return purpose;
 } 
 public void setPurpose (String purpose ) { 
 this.purpose = purpose ; 
 } 
 public String getAuthority () { 
 return authority;
 } 
 public void setAuthority (String authority ) { 
 this.authority = authority ; 
 } 
 public String getMaterial () { 
 return material;
 } 
 public void setMaterial (String material ) { 
 this.material = material ; 
 } 
 public String getDetail () { 
 return detail;
 } 
 public void setDetail (String detail ) { 
 this.detail = detail ; 
 } 
 public String getStorage () { 
 return storage;
 } 
 public void setStorage (String storage ) { 
 this.storage = storage ; 
 } 
 public String getHandoverType () { 
 return handoverType;
 } 
 public void setHandoverType (String handoverType ) { 
 this.handoverType = handoverType ; 
 } 
 public String getHandoverDetail () { 
 return handoverDetail;
 } 
 public void setHandoverDetail (String handoverDetail ) { 
 this.handoverDetail = handoverDetail ; 
 } 
 public String getHandoverModel () { 
 return handoverModel;
 } 
 public void setHandoverModel (String handoverModel ) { 
 this.handoverModel = handoverModel ; 
 } 
 public String getHandoverContent () { 
 return handoverContent;
 } 
 public void setHandoverContent (String handoverContent ) { 
 this.handoverContent = handoverContent ; 
 } 
 public String getHandoverStorage () { 
 return handoverStorage;
 } 
 public void setHandoverStorage (String handoverStorage ) { 
 this.handoverStorage = handoverStorage ; 
 } 
 public String getStockType () { 
 return stockType;
 } 
 public void setStockType (String stockType ) { 
 this.stockType = stockType ; 
 } 
 public String getStockContent () { 
 return stockContent;
 } 
 public void setStockContent (String stockContent ) { 
 this.stockContent = stockContent ; 
 } 
 public String getSuppliesType () { 
 return suppliesType;
 } 
 public void setSuppliesType (String suppliesType ) { 
 this.suppliesType = suppliesType ; 
 } 
 public String getSuppliesContent () { 
 return suppliesContent;
 } 
 public void setSuppliesContent (String suppliesContent ) { 
 this.suppliesContent = suppliesContent ; 
 } 
 }