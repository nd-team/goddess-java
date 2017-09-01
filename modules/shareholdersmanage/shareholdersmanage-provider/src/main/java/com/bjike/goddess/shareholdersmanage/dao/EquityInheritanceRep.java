package com.bjike.goddess.shareholdersmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.shareholdersmanage.dto.EquityInheritanceDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityInheritance;

/**
* 股权继承持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:06 ]
* @Description:	[ 股权继承持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EquityInheritanceRep extends JpaRep<EquityInheritance ,EquityInheritanceDTO> { 

 }