package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.DriverBO;
import com.bjike.goddess.carinfo.entity.Driver;
import com.bjike.goddess.carinfo.service.DriverSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 旧服务器上的车辆信息业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-08 05:56 ]
* @Description:	[ 旧服务器上的车辆信息业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("driverApiImpl")
public class DriverApiImpl implements DriverAPI  {
    @Autowired
    private DriverSer driverSer;
    @Override
    public DriverBO findOne(String driverId) throws SerException {
        return driverSer.findOne(driverId);
    }
}