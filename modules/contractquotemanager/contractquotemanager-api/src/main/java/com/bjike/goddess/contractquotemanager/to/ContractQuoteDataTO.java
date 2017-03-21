package com.bjike.goddess.contractquotemanager.to;

import com.bjike.goddess.common.api.to.BaseTO;
/**
* 合同单价资料信息
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:01:53.314 ]
* @Description:	[ 合同单价资料信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractQuoteDataTO extends BaseTO { 

/**
* 客户名称
*/
 private String  customerName; 

/**
* 项目
*/
 private String  project; 

/**
* 地区
*/
 private String  area; 

/**
* 适用年度开始时间
*/
 private String  suitableDateStart; 

/**
* 适用年度结束时间
*/
 private String  suitableDateEnd; 

/**
* 适用状态,0过期,1正常
*/
 private Integer  useStatus; 

/**
* 附件
*/
// private Set<ModuleAttchment>  attchmentSet;



 public String getCustomerName () { 
 return customerName;
 } 
 public void setCustomerName (String customerName ) { 
 this.customerName = customerName ; 
 } 
 public String getProject () { 
 return project;
 } 
 public void setProject (String project ) { 
 this.project = project ; 
 } 
 public String getArea () { 
 return area;
 } 
 public void setArea (String area ) { 
 this.area = area ; 
 } 
 public String getSuitableDateStart () { 
 return suitableDateStart;
 } 
 public void setSuitableDateStart (String suitableDateStart ) { 
 this.suitableDateStart = suitableDateStart ; 
 } 
 public String getSuitableDateEnd () { 
 return suitableDateEnd;
 } 
 public void setSuitableDateEnd (String suitableDateEnd ) { 
 this.suitableDateEnd = suitableDateEnd ; 
 } 
 public Integer getUseStatus () { 
 return useStatus;
 } 
 public void setUseStatus (Integer useStatus ) { 
 this.useStatus = useStatus ; 
 } 
// public Set<ModuleAttchment> getAttchmentSet () {
// return attchmentSet;
// }
// public void setAttchmentSet (Set<ModuleAttchment> attchmentSet ) {
// this.attchmentSet = attchmentSet ;
// }
//
}