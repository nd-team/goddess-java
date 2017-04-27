package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.entity.AttachedEnd;

/**
* 挂靠到期持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-24 10:04 ]
* @Description:	[ 挂靠到期持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AttachedEndRep extends JpaRep<AttachedEnd ,AttachedEndDTO> { 

 }