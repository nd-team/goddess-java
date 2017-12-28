package com.bjike.goddess.staffing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffing.dto.ConfigurationPlanDTO;
import com.bjike.goddess.staffing.entity.ConfigurationPlan;

/**
* 人数配置-计划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-29 10:33 ]
* @Description:	[ 人数配置-计划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConfigurationPlanRep extends JpaRep<ConfigurationPlan ,ConfigurationPlanDTO> { 

 }