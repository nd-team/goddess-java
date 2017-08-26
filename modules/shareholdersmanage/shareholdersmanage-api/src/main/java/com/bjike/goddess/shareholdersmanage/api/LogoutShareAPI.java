package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.LogoutShareBO;
import com.bjike.goddess.shareholdersmanage.bo.LogoutShareLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.LogoutShareDTO;
import com.bjike.goddess.shareholdersmanage.to.LogoutShareTO;

import java.util.List;

/**
* 注销股东业务接口
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 03:55 ]
* @Description:	[ 注销股东业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface LogoutShareAPI  {
 /**
  * 注销股东列表总条数
  */
 default Long countLogout(LogoutShareDTO logoutShareDTO) throws SerException {
  return null;
 }

 /**
  * 一个注销股东
  *
  * @return class LogoutShareBO
  */
 default LogoutShareBO getOne(String id) throws SerException {
  return null;
 }

 /**
  * 注销股东列表
  *
  * @param logoutShareDTO 注销股东dto
  * @return class LogoutShareBO
  * @throws SerException
  */
 default List<LogoutShareBO> findList(LogoutShareDTO logoutShareDTO) throws SerException {
  return null;
 }

 /**
  * 注销股东添加
  *
  * @param logoutShareTO 注销股东数据to
  * @return class LogoutShareBO
  * @throws SerException
  */
 default LogoutShareBO save(LogoutShareTO logoutShareTO) throws SerException {
  return null;
 }

 /**
  * 注销股东编辑
  *
  * @param logoutShareTO 注销股东数据to
  * @return class LogoutShareBO
  * @throws SerException
  */
 default LogoutShareBO edit(LogoutShareTO logoutShareTO) throws SerException {
  return null;
 }

 /**
  * 根据id删除注销股东
  *
  * @param id
  * @throws SerException
  */
 default void delete(String id) throws SerException {

 }

 /**
  * 根据注销股东名称链接数据
  *
  * @param logoutShareName
  * @throws SerException
  */
 default LogoutShareLinkDateBO linkDateByName(String logoutShareName) throws SerException {
  return null;
 }
 }