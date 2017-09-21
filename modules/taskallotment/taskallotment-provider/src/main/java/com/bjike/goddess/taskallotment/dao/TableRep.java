package com.bjike.goddess.taskallotment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.entity.Table;

/**
* 项目表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-14 11:58 ]
* @Description:	[ 项目表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TableRep extends JpaRep<Table ,TableDTO> { 

 }