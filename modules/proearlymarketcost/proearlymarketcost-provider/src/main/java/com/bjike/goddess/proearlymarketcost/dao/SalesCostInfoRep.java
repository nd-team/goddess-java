package com.bjike.goddess.proearlymarketcost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.proearlymarketcost.dto.SalesCostInfoDTO;
import com.bjike.goddess.proearlymarketcost.entity.SalesCostInfo;

/**
* 销售费用信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:36 ]
* @Description:	[ 销售费用信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalesCostInfoRep extends JpaRep<SalesCostInfo ,SalesCostInfoDTO> { 

 }