package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.dto.CustomTitleDTO;

import java.util.List;

/**
* 自定义字段业务接口
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-14 02:35 ]
* @Description:	[ 自定义字段业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomTitleAPI  {
 /**
  * 根据条件获取节点id
  * @param customTitleDTO
  * @return
  * @throws SerException
  */
   List<String> nodeId(CustomTitleDTO customTitleDTO) throws SerException;
 }