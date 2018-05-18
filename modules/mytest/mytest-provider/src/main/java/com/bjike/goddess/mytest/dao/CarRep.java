package com.bjike.goddess.mytest.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.mytest.dto.CarDTO;
import com.bjike.goddess.mytest.entity.Car;

/**
* 测试用类 汽车持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-16 11:13 ]
* @Description:	[ 测试用类 汽车持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CarRep extends JpaRep<Car ,CarDTO> { 

 }