package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.entity.SecureCart;

/**
* 社保卡基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-25 01:44 ]
* @Description:	[ 社保卡基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SecureCartRep extends JpaRep<SecureCart,SecureCartDTO> {

 }