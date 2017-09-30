package com.bjike.goddess.taskallotment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.taskallotment.dto.FinishCountEmailDTO;
import com.bjike.goddess.taskallotment.entity.FinishCountEmail;

/**
* 完成情况汇总设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-28 09:31 ]
* @Description:	[ 完成情况汇总设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FinishCountEmailRep extends JpaRep<FinishCountEmail ,FinishCountEmailDTO> { 

 }