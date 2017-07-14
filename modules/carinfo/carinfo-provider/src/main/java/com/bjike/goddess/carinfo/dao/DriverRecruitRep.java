package com.bjike.goddess.carinfo.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.entity.DriverRecruit;

/**
* 司机招聘信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jason ]
* @Date:			[  2017-07-13 08:27 ]
* @Description:	[ 司机招聘信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DriverRecruitRep extends JpaRep<DriverRecruit ,DriverRecruitDTO> { 

 }