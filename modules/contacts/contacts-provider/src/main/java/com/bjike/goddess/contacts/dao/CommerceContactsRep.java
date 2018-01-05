package com.bjike.goddess.contacts.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contacts.dto.CommerceContactsDTO;
import com.bjike.goddess.contacts.entity.CommerceContacts;

/**
* 商务通讯录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-29 06:04 ]
* @Description:	[ 商务通讯录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CommerceContactsRep extends JpaRep<CommerceContacts ,CommerceContactsDTO> { 

 }