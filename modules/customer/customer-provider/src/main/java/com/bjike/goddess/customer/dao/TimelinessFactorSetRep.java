package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.TimelinessFactorSetDTO;
import com.bjike.goddess.customer.entity.TimelinessFactorSet;

/**
* 时效性因素层设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-01 01:49 ]
* @Description:	[ 时效性因素层设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TimelinessFactorSetRep extends JpaRep<TimelinessFactorSet ,TimelinessFactorSetDTO> { 

 }