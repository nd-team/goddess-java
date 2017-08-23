package com.bjike.goddess.version.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.version.dto.HelpDTO;
import com.bjike.goddess.version.entity.Help;

/**
* 帮助与解答持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-04 03:07 ]
* @Description:	[ 帮助与解答持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface HelpRep extends JpaRep<Help ,HelpDTO> { 

 }