package com.bjike.goddess.workhandover.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workhandover.dto.workTimeSpecificationDTO;
import com.bjike.goddess.workhandover.entity.workTimeSpecification;

/**
* 工作交接时间规范持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenyang ]
* @Date:			[  2017-11-11 04:47 ]
* @Description:	[ 工作交接时间规范持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface workTimeSpecificationRep extends JpaRep<workTimeSpecification ,workTimeSpecificationDTO> { 

 }