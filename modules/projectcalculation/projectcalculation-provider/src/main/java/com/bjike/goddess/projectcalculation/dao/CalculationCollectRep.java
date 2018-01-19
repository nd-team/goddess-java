package com.bjike.goddess.projectcalculation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectcalculation.dto.CalculationCollectDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationCollect;

/**
* 项目测算管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-12 10:27 ]
* @Description:	[ 项目测算管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CalculationCollectRep extends JpaRep<CalculationCollect ,CalculationCollectDTO> { 

 }