package com.bjike.goddess.businessinteraction.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;

/**
* 商业能力互动联系持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-28 03:06 ]
* @Description:	[ 商业能力互动联系持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InteractionRelationRep extends JpaRep<InteractionRelation ,InteractionRelationDTO> { 

 }