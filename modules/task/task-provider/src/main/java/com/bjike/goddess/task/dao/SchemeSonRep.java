package com.bjike.goddess.task.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.task.dto.SchemeSonDTO;
import com.bjike.goddess.task.entity.SchemeSon;

/**
* 汇总方案子表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-18 04:56 ]
* @Description:	[ 汇总方案子表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SchemeSonRep extends JpaRep<SchemeSon ,SchemeSonDTO> { 

 }