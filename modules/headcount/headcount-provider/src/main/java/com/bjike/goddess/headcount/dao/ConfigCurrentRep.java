package com.bjike.goddess.headcount.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.headcount.dto.ConfigCurrentDTO;
import com.bjike.goddess.headcount.entity.ConfigCurrent;

/**
* 当前部门人数配置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:19:57.638 ]
* @Description:	[ 当前部门人数配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConfigCurrentRep extends JpaRep<ConfigCurrent,ConfigCurrentDTO> {

 }