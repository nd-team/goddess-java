package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.entity.Attached;

/**
* 挂靠持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-21 02:12 ]
* @Description:	[ 挂靠持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AttachedRep extends JpaRep<Attached ,AttachedDTO> { 

 }