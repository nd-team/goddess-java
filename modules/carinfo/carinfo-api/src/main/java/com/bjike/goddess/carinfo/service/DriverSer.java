package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.DriverBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.carinfo.entity.Driver;
import com.bjike.goddess.carinfo.dto.DriverDTO;

/**
* 旧服务器上的车辆信息业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-08 05:56 ]
* @Description:	[ 旧服务器上的车辆信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DriverSer extends Ser<Driver, DriverDTO> {
    /**
     * 根据id查询数据
     */
    DriverBO findOne(String driverId) throws SerException;
 }