package com.bjike.goddess.businessprojectmanage.bo;

import com.bjike.goddess.businessprojectmanage.enums.CusPermissionType;
import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.util.List;

/**
* 客户权限设置业务传输对象
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-12 05:56 ]
* @Description:	[ 客户权限设置业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class CusPermissionBO extends BaseBO { 

/**
* 辅助id
*/
 private String  idFlag; 

/**
* 描述
*/
 private String  description; 

/**
* 操作对象
*/
 private String  operator; 

/**
* 类型
*/
 private CusPermissionType type;

 /**
  * 创建时间
  */
 private String createTime;

 /**
  * 修改时间
  */
 private String modifyTime;

 /**
  * list返回的操作对象
  */
 private List<CusOperateBO> cusOperateBO;



 public String getIdFlag () { 
 return idFlag;
 } 
 public void setIdFlag (String idFlag ) { 
 this.idFlag = idFlag ; 
 } 
 public String getDescription () { 
 return description;
 } 
 public void setDescription (String description ) { 
 this.description = description ; 
 } 
 public String getOperator () { 
 return operator;
 } 
 public void setOperator (String operator ) { 
 this.operator = operator ; 
 } 
 public CusPermissionType getType () { 
 return type;
 } 
 public void setType (CusPermissionType type ) { 
 this.type = type ; 
 }

 public String getCreateTime() {
  return createTime;
 }

 public void setCreateTime(String createTime) {
  this.createTime = createTime;
 }

 public String getModifyTime() {
  return modifyTime;
 }

 public void setModifyTime(String modifyTime) {
  this.modifyTime = modifyTime;
 }

 public List<CusOperateBO> getCusOperateBO() {
  return cusOperateBO;
 }

 public void setCusOperateBO(List<CusOperateBO> cusOperateBO) {
  this.cusOperateBO = cusOperateBO;
 }
}