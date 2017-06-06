package com.bjike.goddess.negotiatemeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.negotiatemeeting.dto.ReadyDTO;
import com.bjike.goddess.negotiatemeeting.entity.Ready;

/**
* 协商前准备信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-31 03:39 ]
* @Description:	[ 协商前准备信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ReadyRep extends JpaRep<Ready ,ReadyDTO> { 

 }