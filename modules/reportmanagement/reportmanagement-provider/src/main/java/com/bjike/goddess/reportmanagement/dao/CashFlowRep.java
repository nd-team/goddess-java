package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.CashFlowDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlow;

/**
* 现金流量表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-21 03:54 ]
* @Description:	[ 现金流量表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CashFlowRep extends JpaRep<CashFlow ,CashFlowDTO> { 

 }