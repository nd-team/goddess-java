package com.bjike.goddess.proearlymarketcost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.proearlymarketcost.dto.CostBenefitAnalysisDTO;
import com.bjike.goddess.proearlymarketcost.entity.CostBenefitAnalysis;

/**
* 费用效益分析持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:58 ]
* @Description:	[ 费用效益分析持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CostBenefitAnalysisRep extends JpaRep<CostBenefitAnalysis ,CostBenefitAnalysisDTO> { 

 }