package com.bjike.goddess.announcement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.announcement.dto.ClassDTO;
import com.bjike.goddess.announcement.entity.Class;

/**
* 公告分类持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-07 02:20 ]
* @Description:	[ 公告分类持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ClassRep extends JpaRep<Class ,ClassDTO> { 

 }