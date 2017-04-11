package com.bjike.goddess.bonus.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bonus.dto.PerformanceIndicatorDTO;
import com.bjike.goddess.bonus.entity.PerformanceIndicator;

/**
* 绩效指标持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-08 05:40 ]
* @Description:	[ 绩效指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PerformanceIndicatorRep extends JpaRep<PerformanceIndicator ,PerformanceIndicatorDTO> { 

 }