package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.ExtralOverWorkSonDTO;
import com.bjike.goddess.attendance.entity.ExtralOverWorkSon;

/**
* 补班子表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-21 10:32 ]
* @Description:	[ 补班子表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ExtralOverWorkSonRep extends JpaRep<ExtralOverWorkSon ,ExtralOverWorkSonDTO> { 

 }