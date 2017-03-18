package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CustomerDetail;

/**
* 客户详细信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-16T09:41:13.475 ]
* @Description:	[ 客户详细信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomerDetailRep extends JpaRep<CustomerDetail ,CustomerDetailDTO> { 

 }