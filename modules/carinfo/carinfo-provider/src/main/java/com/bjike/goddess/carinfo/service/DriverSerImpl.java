package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.DriverBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.carinfo.dto.DriverDTO;
import com.bjike.goddess.carinfo.entity.Driver;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 旧服务器上的车辆信息业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-08 05:56 ]
* @Description:	[ 旧服务器上的车辆信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="carinfoSerCache")
@Service
public class DriverSerImpl extends ServiceImpl<Driver, DriverDTO> implements DriverSer {

    @Override
    public DriverBO findOne(String driverId) throws SerException {
        Driver driver = super.findById(driverId);
        DriverBO driverBO = BeanTransform.copyProperties(driver,DriverBO.class);
        return driverBO;
    }
}