package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.PayStockDTO;
import com.bjike.goddess.fundcheck.entity.PayStock;

/**
* 支付给股东持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-07-01 01:55 ]
* @Description:	[ 支付给股东持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PayStockRep extends JpaRep<PayStock ,PayStockDTO> { 

 }