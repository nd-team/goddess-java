package com.bjike.goddess.abilitydisplay.to;

import com.bjike.goddess.abilitydisplay.entity.ComCertificate;
import com.bjike.goddess.common.api.to.BaseTO;
/**
* 公司认证
* @Author:			[ wanyi ]
* @Date:			[  2018-01-08 05:31 ]
* @Description:	[ 公司认证 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CompanyAuthTO extends BaseTO { 

/**
* 中兴技能认证证书数量
*/
 private Integer  zteNum; 

/**
* 华为技能认证证书数量
*/
 private Integer  huaweiNum; 

/**
* 公司证书
*/
 private ComCertificate certificate;



 public Integer getZteNum () { 
 return zteNum;
 } 
 public void setZteNum (Integer zteNum ) { 
 this.zteNum = zteNum ; 
 } 
 public Integer getHuaweiNum () { 
 return huaweiNum;
 } 
 public void setHuaweiNum (Integer huaweiNum ) { 
 this.huaweiNum = huaweiNum ; 
 } 
 public ComCertificate getCertificate () { 
 return certificate;
 } 
 public void setCertificate (ComCertificate certificate ) { 
 this.certificate = certificate ; 
 } 
 }