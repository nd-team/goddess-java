package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 买入记录表
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 10:09 ]
* @Description:	[ 买入记录表 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "staffshares_buyschedule")
public class Buyschedule extends BaseEntity { 

/**
* 持股人
*/
@Column(name = "shareholder",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '持股人'"  ) 
 private String  shareholder; 

/**
* 方案代码
*/
@Column(name = "code",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '方案代码'"  ) 
 private String  code; 

/**
* 方案名称
*/
@Column(name = "name",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '方案名称'"  ) 
 private String  name; 

/**
* 购入股数
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '购入股数'"  ) 
 private int  purchaseNum; 

/**
* 买入价格
*/
@Column(name = "buyPrice",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '买入价格'"  ) 
 private Double  buyPrice; 

/**
* 发行价格
*/
@Column(name = "price",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '发行价格'"  ) 
 private Double  price; 

/**
* 买入金额
*/
@Column(name = "totalBuyPrice",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '买入金额'"  ) 
 private Double  totalBuyPrice; 

/**
* 发行金额
*/
@Column(name = "totalPrice",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '发行金额'"  ) 
 private Double  totalPrice; 

/**
* 出售人
*/
@Column(name = "sell",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '出售人'"  ) 
 private String  sell; 

/**
* 交易时间
*/
@Column(name = "time",nullable = false,columnDefinition = "DATETIME   COMMENT '交易时间'"  ) 
 private LocalDateTime  time; 



 public String getShareholder () { 
 return shareholder;
 } 
 public void setShareholder (String shareholder ) { 
 this.shareholder = shareholder ; 
 } 
 public String getCode () { 
 return code;
 } 
 public void setCode (String code ) { 
 this.code = code ; 
 } 
 public String getName () { 
 return name;
 } 
 public void setName (String name ) { 
 this.name = name ; 
 } 
 public int getPurchaseNum () { 
 return purchaseNum;
 } 
 public void setPurchaseNum (int purchaseNum ) { 
 this.purchaseNum = purchaseNum ; 
 } 
 public Double getBuyPrice () { 
 return buyPrice;
 } 
 public void setBuyPrice (Double buyPrice ) { 
 this.buyPrice = buyPrice ; 
 } 
 public Double getPrice () { 
 return price;
 } 
 public void setPrice (Double price ) { 
 this.price = price ; 
 } 
 public Double getTotalBuyPrice () { 
 return totalBuyPrice;
 } 
 public void setTotalBuyPrice (Double totalBuyPrice ) { 
 this.totalBuyPrice = totalBuyPrice ; 
 } 
 public Double getTotalPrice () { 
 return totalPrice;
 } 
 public void setTotalPrice (Double totalPrice ) { 
 this.totalPrice = totalPrice ; 
 } 
 public String getSell () { 
 return sell;
 } 
 public void setSell (String sell ) { 
 this.sell = sell ; 
 } 
 public LocalDateTime getTime () { 
 return time;
 } 
 public void setTime (LocalDateTime time ) { 
 this.time = time ; 
 } 
 }