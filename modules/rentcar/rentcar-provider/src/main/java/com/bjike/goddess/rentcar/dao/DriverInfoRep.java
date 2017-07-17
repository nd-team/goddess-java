package com.bjike.goddess.rentcar.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rentcar.dto.DriverInfoDTO;
import com.bjike.goddess.rentcar.entity.DriverInfo;

/**
* 租车协议管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jason ]
* @Date:			[  2017-07-13 07:47 ]
* @Description:	[ 租车协议管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DriverInfoRep extends JpaRep<DriverInfo ,DriverInfoDTO> { 

 }