package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.CashFlowDataDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlowData;

/**
* 现金流量资料表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-20 03:02 ]
* @Description:	[ 现金流量资料表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CashFlowDataRep extends JpaRep<CashFlowData ,CashFlowDataDTO> { 

 }