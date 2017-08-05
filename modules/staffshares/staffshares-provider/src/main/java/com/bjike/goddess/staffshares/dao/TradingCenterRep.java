package com.bjike.goddess.staffshares.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffshares.dto.TradingCenterDTO;
import com.bjike.goddess.staffshares.entity.TradingCenter;

/**
* 交易中心持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 09:05 ]
* @Description:	[ 交易中心持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TradingCenterRep extends JpaRep<TradingCenter ,TradingCenterDTO> { 

 }