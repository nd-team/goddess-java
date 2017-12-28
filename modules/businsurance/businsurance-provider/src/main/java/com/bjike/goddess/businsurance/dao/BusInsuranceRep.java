package com.bjike.goddess.businsurance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;
import com.bjike.goddess.businsurance.entity.BusInsurance;

/**
* 商业保险方案持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-21 09:44 ]
* @Description:	[ 商业保险方案持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BusInsuranceRep extends JpaRep<BusInsurance ,BusInsuranceDTO> { 

 }