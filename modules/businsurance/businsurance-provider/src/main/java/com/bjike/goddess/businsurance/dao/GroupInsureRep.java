package com.bjike.goddess.businsurance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.entity.GroupInsure;

/**
* 团体意外险信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-22 10:02 ]
* @Description:	[ 团体意外险信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface GroupInsureRep extends JpaRep<GroupInsure ,GroupInsureDTO> { 

 }