package com.bjike.goddess.projectmarketfee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisCountDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysisCount;

/**
* 费用效益分析业务汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-15 03:13 ]
* @Description:	[ 费用效益分析业务汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CostAnalysisCountRep extends JpaRep<CostAnalysisCount ,CostAnalysisCountDTO> { 

 }