package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.TimelinessFactorWeightDTO;
import com.bjike.goddess.customer.entity.TimelinessFactorWeight;

/**
* 时效性因素层权重持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-01 01:52 ]
* @Description:	[ 时效性因素层权重持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TimelinessFactorWeightRep extends JpaRep<TimelinessFactorWeight ,TimelinessFactorWeightDTO> { 

 }