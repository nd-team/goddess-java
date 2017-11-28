package com.bjike.goddess.workhandover.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workhandover.dto.ObligationsDTO;
import com.bjike.goddess.workhandover.entity.Obligations;

/**
* 工作交接时间规范持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenyang ]
* @Date:			[  2017-11-13 06:26 ]
* @Description:	[ 工作交接时间规范持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ObligationsRep extends JpaRep<Obligations ,ObligationsDTO> { 

 }