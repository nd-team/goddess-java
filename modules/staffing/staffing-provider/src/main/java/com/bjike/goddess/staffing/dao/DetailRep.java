package com.bjike.goddess.staffing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffing.dto.DetailDTO;
import com.bjike.goddess.staffing.entity.Detail;

/**
* 人工成本计划详细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-31 02:59 ]
* @Description:	[ 人工成本计划详细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DetailRep extends JpaRep<Detail,DetailDTO> {

 }