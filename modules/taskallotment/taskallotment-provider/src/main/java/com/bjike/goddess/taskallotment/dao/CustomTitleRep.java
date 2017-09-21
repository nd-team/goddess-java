package com.bjike.goddess.taskallotment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.taskallotment.dto.CustomTitleDTO;
import com.bjike.goddess.taskallotment.entity.CustomTitle;

/**
* 自定义字段持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-14 02:35 ]
* @Description:	[ 自定义字段持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CustomTitleRep extends JpaRep<CustomTitle ,CustomTitleDTO> { 

 }