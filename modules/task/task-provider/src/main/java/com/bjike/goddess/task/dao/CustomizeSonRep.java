package com.bjike.goddess.task.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.task.dto.CustomizeSonDTO;
import com.bjike.goddess.task.entity.CustomizeSon;

/**
* 自定义汇总子表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-18 11:09 ]
* @Description:	[ 自定义汇总子表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomizeSonRep extends JpaRep<CustomizeSon ,CustomizeSonDTO> { 

 }