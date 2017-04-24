package com.bjike.goddess.businsurance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.entity.TowerInsure;

/**
* 塔工意外险信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-22 09:30 ]
* @Description:	[ 塔工意外险信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TowerInsureRep extends JpaRep<TowerInsure ,TowerInsureDTO> { 

 }