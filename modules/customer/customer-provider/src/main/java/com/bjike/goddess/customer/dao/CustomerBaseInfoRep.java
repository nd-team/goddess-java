package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;

/**
* 客户基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-15T16:23:28.064 ]
* @Description:	[ 客户基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomerBaseInfoRep extends JpaRep<CustomerBaseInfo ,CustomerBaseInfoDTO> { 

 }