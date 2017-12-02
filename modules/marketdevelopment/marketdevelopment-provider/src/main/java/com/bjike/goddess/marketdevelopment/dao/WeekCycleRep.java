package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.WeekCycleDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekCycle;

/**
* 周计划的周期持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:37 ]
* @Description:	[ 周计划的周期持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WeekCycleRep extends JpaRep<WeekCycle ,WeekCycleDTO> { 

 }