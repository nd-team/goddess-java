package com.bjike.goddess.market.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.entity.MarketEmail;

/**
* 市场邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-03-22T19:08:18.889 ]
* @Description:	[ 市场邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MarketEmailRep extends JpaRep<MarketEmail,MarketEmailDTO> {

 }