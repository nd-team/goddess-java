package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.CashAnalyseDTO;
import com.bjike.goddess.reportmanagement.entity.CashAnalyse;

/**
* 现金流量分析持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-24 10:20 ]
* @Description:	[ 现金流量分析持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CashAnalyseRep extends JpaRep<CashAnalyse ,CashAnalyseDTO> { 

 }