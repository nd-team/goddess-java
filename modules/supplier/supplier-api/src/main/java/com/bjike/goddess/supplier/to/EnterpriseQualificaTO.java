package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 企业资质
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 03:58 ]
* @Description:	[ 企业资质 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class EnterpriseQualificaTO extends BaseTO { 

/**
* 证书名称
*/
 private String  certificateName; 

/**
* 证书编号
*/
 private String  certificateNum; 

/**
* 有效期限
*/
 private String  validTerm; 

/**
* 颁发单位
*/
 private String  issuedUnit; 

/**
* 资质等级
*/
 private String  qualifiLevel; 

/**
* 备注
*/
 private String  remark; 



 public String getCertificateName () { 
 return certificateName;
 } 
 public void setCertificateName (String certificateName ) { 
 this.certificateName = certificateName ; 
 } 
 public String getCertificateNum () { 
 return certificateNum;
 } 
 public void setCertificateNum (String certificateNum ) { 
 this.certificateNum = certificateNum ; 
 } 
 public String getValidTerm () { 
 return validTerm;
 } 
 public void setValidTerm (String validTerm ) { 
 this.validTerm = validTerm ; 
 } 
 public String getIssuedUnit () { 
 return issuedUnit;
 } 
 public void setIssuedUnit (String issuedUnit ) { 
 this.issuedUnit = issuedUnit ; 
 } 
 public String getQualifiLevel () { 
 return qualifiLevel;
 } 
 public void setQualifiLevel (String qualifiLevel ) { 
 this.qualifiLevel = qualifiLevel ; 
 } 
 public String getRemark () { 
 return remark;
 } 
 public void setRemark (String remark ) { 
 this.remark = remark ; 
 } 
 }