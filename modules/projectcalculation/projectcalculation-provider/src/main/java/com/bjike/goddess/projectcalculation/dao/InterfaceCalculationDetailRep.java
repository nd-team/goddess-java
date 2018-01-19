package com.bjike.goddess.projectcalculation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.InterfaceCalculationDetail;

/**
* 测算决策持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-08 03:04 ]
* @Description:	[ 测算决策持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InterfaceCalculationDetailRep extends JpaRep<InterfaceCalculationDetail ,InterfaceCalculationDetailDTO> { 

 }