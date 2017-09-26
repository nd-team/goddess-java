package com.bjike.goddess.taskallotment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.taskallotment.dto.TimeSetDTO;
import com.bjike.goddess.taskallotment.entity.TimeSet;

/**
* 标准工时设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-15 11:23 ]
* @Description:	[ 标准工时设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TimeSetRep extends JpaRep<TimeSet ,TimeSetDTO> { 

 }