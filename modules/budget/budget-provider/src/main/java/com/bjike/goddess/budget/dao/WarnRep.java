package com.bjike.goddess.budget.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.budget.dto.WarnDTO;
import com.bjike.goddess.budget.entity.Warn;

/**
* 预警持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-17 10:35 ]
* @Description:	[ 预警持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WarnRep extends JpaRep<Warn ,WarnDTO> { 

 }