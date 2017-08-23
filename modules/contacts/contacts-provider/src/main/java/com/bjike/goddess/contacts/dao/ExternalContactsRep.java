package com.bjike.goddess.contacts.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.entity.ExternalContacts;

/**
* 外部通讯录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-29 05:26 ]
* @Description:	[ 外部通讯录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ExternalContactsRep extends JpaRep<ExternalContacts ,ExternalContactsDTO> { 

 }