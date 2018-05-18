package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;

import java.util.Map;

/**
* 岗位工作明细表-模块表业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-12 01:58 ]
* @Description:	[ 岗位工作明细表-模块表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ModulesAPI  {
 /**
  * 通过用户名查询模块与职位
  * @param username
  * @return
  */
  Map<String,String> findModuleAndPost(String username)throws SerException;

 }