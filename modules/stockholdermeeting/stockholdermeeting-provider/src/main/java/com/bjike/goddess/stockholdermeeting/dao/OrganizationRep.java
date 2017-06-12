package com.bjike.goddess.stockholdermeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.stockholdermeeting.dto.OrganizationDTO;
import com.bjike.goddess.stockholdermeeting.entity.Organization;

/**
* 股东大会组织内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-06 05:46 ]
* @Description:	[ 股东大会组织内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OrganizationRep extends JpaRep<Organization ,OrganizationDTO> { 

 }