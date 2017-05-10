package com.bjike.goddess.projectmarketfee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysis;

/**
* 费用效益分析持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-09 04:35 ]
* @Description:	[ 费用效益分析持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CostAnalysisRep extends JpaRep<CostAnalysis ,CostAnalysisDTO> { 

 }