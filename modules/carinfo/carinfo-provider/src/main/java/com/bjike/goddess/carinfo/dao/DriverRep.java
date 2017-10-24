package com.bjike.goddess.carinfo.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.carinfo.dto.DriverDTO;
import com.bjike.goddess.carinfo.entity.Driver;

/**
* 旧服务器上的车辆信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-08 05:56 ]
* @Description:	[ 旧服务器上的车辆信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DriverRep extends JpaRep<Driver ,DriverDTO> { 

 }