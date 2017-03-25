package com.bjike.goddess.headcount.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.headcount.dto.ConfigPlanDTO;
import com.bjike.goddess.headcount.entity.ConfigPlan;

/**
* 部门人数配置(计划)持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:18:34.795 ]
* @Description:	[ 部门人数配置(计划)持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ConfigPlanRep extends JpaRep<ConfigPlan,ConfigPlanDTO> {

 }