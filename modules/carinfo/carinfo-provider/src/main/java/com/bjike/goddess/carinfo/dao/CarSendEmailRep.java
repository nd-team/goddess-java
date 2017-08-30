package com.bjike.goddess.carinfo.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.carinfo.dto.CarSendEmailDTO;
import com.bjike.goddess.carinfo.entity.CarSendEmail;

/**
* 测试持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-25 09:50 ]
* @Description:	[ 测试持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CarSendEmailRep extends JpaRep<CarSendEmail ,CarSendEmailDTO> { 

 }