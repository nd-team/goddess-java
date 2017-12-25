package com.bjike.goddess.contacts.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contacts.dto.OtherContactsDTO;
import com.bjike.goddess.contacts.entity.OtherContacts;

/**
* 其他通讯录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-29 05:44 ]
* @Description:	[ 其他通讯录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OtherContactsRep extends JpaRep<OtherContacts ,OtherContactsDTO> { 

 }