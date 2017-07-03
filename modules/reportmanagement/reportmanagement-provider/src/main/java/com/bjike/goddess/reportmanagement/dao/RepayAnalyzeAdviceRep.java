package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.RepayAnalyzeAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.RepayAnalyzeAdvice;

/**
* 偿还能力分析管理建议设计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-30 04:04 ]
* @Description:	[ 偿还能力分析管理建议设计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RepayAnalyzeAdviceRep extends JpaRep<RepayAnalyzeAdvice ,RepayAnalyzeAdviceDTO> { 

 }