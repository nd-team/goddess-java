package com.bjike.goddess.carinfo.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.entity.DriverInfo;

/**
* 车辆信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jason ]
* @Date:			[  2017-07-13 07:46 ]
* @Description:	[ 车辆信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DriverInfoRep extends JpaRep<DriverInfo ,DriverInfoDTO> { 

 }