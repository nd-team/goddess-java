package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.IntelligentMsgDTO;
import com.bjike.goddess.recruit.entity.IntelligentMsg;

/**
* 智能消息提醒持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wany ]
* @Date:			[  2018-01-16 11:07 ]
* @Description:	[ 智能消息提醒持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface IntelligentMsgRep extends JpaRep<IntelligentMsg ,IntelligentMsgDTO> { 

 }