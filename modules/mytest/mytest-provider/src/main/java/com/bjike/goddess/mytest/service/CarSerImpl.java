package com.bjike.goddess.mytest.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.mytest.dto.CarDTO;
import com.bjike.goddess.mytest.entity.Car;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 测试用类 汽车业务实现
* @Author:			[ wanyi ]
* @Date:			[  2017-12-16 11:13 ]
* @Description:	[ 测试用类 汽车业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="mytestSerCache")
@Service
public class CarSerImpl extends ServiceImpl<Car, CarDTO> implements CarSer { 

 }