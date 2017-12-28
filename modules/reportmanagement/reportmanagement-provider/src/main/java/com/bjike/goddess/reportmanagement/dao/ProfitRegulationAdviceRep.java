package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitRegulationAdvice;

/**
* 利润增减率分析管理建议设计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-30 06:53 ]
* @Description:	[ 利润增减率分析管理建议设计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProfitRegulationAdviceRep extends JpaRep<ProfitRegulationAdvice ,ProfitRegulationAdviceDTO> { 

 }