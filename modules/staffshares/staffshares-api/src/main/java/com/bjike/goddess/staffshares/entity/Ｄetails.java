package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 交易详情
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 09:23 ]
* @Description:	[ 交易详情 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "staffshares_ｄetails")
public class Ｄetails extends BaseEntity { 

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
* 出售/发行人
*/
@Column(name = "publisher",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '出售/发行人'"  ) 
 private String  publisher; 

/**
* 出售/发行数量
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '出售/发行数量'"  ) 
 private int  number; 

/**
* 出售/发行价格
*/
@Column(name = "price",nullable = false,columnDefinition = "DECIMAL(10,2)   COMMENT '出售/发行价格'"  ) 
 private Double  price; 

/**
* 出售/发行时间
*/
@Column(name = "time",nullable = false,columnDefinition = "DATE   COMMENT '出售/发行时间'"  ) 
 private LocalDate  time; 

/**
* 剩余出售量
*/
@Column(name = "",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '剩余出售量'"  ) 
 private int  sharesNum; 



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
 public String getPublisher () { 
 return publisher;
 } 
 public void setPublisher (String publisher ) { 
 this.publisher = publisher ; 
 } 
 public int getNumber () { 
 return number;
 } 
 public void setNumber (int number ) { 
 this.number = number ; 
 } 
 public Double getPrice () { 
 return price;
 } 
 public void setPrice (Double price ) { 
 this.price = price ; 
 } 
 public LocalDate getTime () { 
 return time;
 } 
 public void setTime (LocalDate time ) { 
 this.time = time ; 
 } 
 public int getSharesNum () { 
 return sharesNum;
 } 
 public void setSharesNum (int sharesNum ) { 
 this.sharesNum = sharesNum ; 
 } 
 }