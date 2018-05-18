package com.bjike.goddess.projectcalculation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDetail;

/**
* 项目测算明细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-05 02:48 ]
* @Description:	[ 项目测算明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CalculationDetailRep extends JpaRep<CalculationDetail ,CalculationDetailDTO> { 

 }