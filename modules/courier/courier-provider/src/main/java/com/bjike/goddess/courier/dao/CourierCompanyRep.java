package com.bjike.goddess.courier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.courier.dto.CourierCompanyDTO;
import com.bjike.goddess.courier.entity.CourierCompany;

/**
* 快递公司信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-28 10:29 ]
* @Description:	[ 快递公司信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CourierCompanyRep extends JpaRep<CourierCompany ,CourierCompanyDTO> { 

 }