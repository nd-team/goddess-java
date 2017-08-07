package com.bjike.goddess.staffing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffing.dto.ConfigurationActualDTO;
import com.bjike.goddess.staffing.entity.ConfigurationActual;

/**
* 人数配置-实际持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-03 11:05 ]
* @Description:	[ 人数配置-实际持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConfigurationActualRep extends JpaRep<ConfigurationActual ,ConfigurationActualDTO> { 

 }