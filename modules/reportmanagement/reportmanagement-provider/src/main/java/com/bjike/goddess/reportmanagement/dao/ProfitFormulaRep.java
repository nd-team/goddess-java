package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.ProfitFormulaDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitFormula;

/**
* 利润增减率分析和变动分析持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-01 05:07 ]
* @Description:	[ 利润增减率分析和变动分析持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProfitFormulaRep extends JpaRep<ProfitFormula ,ProfitFormulaDTO> { 

 }