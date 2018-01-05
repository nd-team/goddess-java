package com.bjike.goddess.contacts.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.entity.InternalContacts;

/**
* 内部通讯录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-29 05:08 ]
* @Description:	[ 内部通讯录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InternalContactsRep extends JpaRep<InternalContacts ,InternalContactsDTO> { 

 }