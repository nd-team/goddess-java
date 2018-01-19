package com.bjike.goddess.projectcalculation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectcalculation.dto.CalculationDecisionsDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDecisions;

/**
* 测算决策持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-07 02:41 ]
* @Description:	[ 测算决策持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CalculationDecisionsRep extends JpaRep<CalculationDecisions ,CalculationDecisionsDTO> { 

 }