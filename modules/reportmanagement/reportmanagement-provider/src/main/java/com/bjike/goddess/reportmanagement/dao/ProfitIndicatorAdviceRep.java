package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.ProfitIndicatorAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitIndicatorAdvice;

/**
* 利润分析指标管理建议设计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-30 05:24 ]
* @Description:	[ 利润分析指标管理建议设计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProfitIndicatorAdviceRep extends JpaRep<ProfitIndicatorAdvice ,ProfitIndicatorAdviceDTO> { 

 }