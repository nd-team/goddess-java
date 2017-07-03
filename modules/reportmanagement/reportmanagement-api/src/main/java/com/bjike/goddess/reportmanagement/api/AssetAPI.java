package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.AssetBO;

/**
* 资产表业务接口
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-19 11:19 ]
* @Description:	[ 资产表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AssetAPI  {
 /**
  * 通过id查找
  *
  * @param id
  * @return
  * @throws SerException
  */
 AssetBO findByID(String id) throws SerException;
 }