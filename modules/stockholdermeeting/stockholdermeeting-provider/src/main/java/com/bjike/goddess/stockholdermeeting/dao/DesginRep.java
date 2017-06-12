package com.bjike.goddess.stockholdermeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.stockholdermeeting.dto.DesginDTO;
import com.bjike.goddess.stockholdermeeting.entity.Desgin;

/**
* 股东大会组织内容设计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-06 05:33 ]
* @Description:	[ 股东大会组织内容设计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DesginRep extends JpaRep<Desgin ,DesginDTO> { 

 }