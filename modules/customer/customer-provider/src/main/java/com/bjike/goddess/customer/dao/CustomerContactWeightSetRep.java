package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.CustomerContactWeightSetDTO;
import com.bjike.goddess.customer.entity.CustomerContactWeightSet;

/**
* 客户接触阶段权重设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-01 10:46 ]
* @Description:	[ 客户接触阶段权重设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomerContactWeightSetRep extends JpaRep<CustomerContactWeightSet ,CustomerContactWeightSetDTO> { 

 }