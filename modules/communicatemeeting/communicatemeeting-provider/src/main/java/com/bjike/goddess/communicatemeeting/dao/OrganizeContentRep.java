package com.bjike.goddess.communicatemeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.communicatemeeting.dto.OrganizeContentDTO;
import com.bjike.goddess.communicatemeeting.entity.OrganizeContent;

/**
* 交流会组织内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-26 02:16 ]
* @Description:	[ 交流会组织内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OrganizeContentRep extends JpaRep<OrganizeContent ,OrganizeContentDTO> { 

 }