package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;

/**
* 客户级别持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-15T17:00:40.879 ]
* @Description:	[ 客户级别持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomerLevelRep extends JpaRep<CustomerLevel ,CustomerLevelDTO> { 

 }