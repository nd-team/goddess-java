package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.CashRateDTO;
import com.bjike.goddess.reportmanagement.entity.CashRate;

/**
* 现金流量比率表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-20 05:15 ]
* @Description:	[ 现金流量比率表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CashRateRep extends JpaRep<CashRate ,CashRateDTO> { 

 }