package com.bjike.goddess.task.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.task.dto.RecordDTO;
import com.bjike.goddess.task.entity.Record;

/**
* 内部协助单记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-16 11:30 ]
* @Description:	[ 内部协助单记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RecordRep extends JpaRep<Record ,RecordDTO> { 

 }