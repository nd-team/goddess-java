package com.bjike.goddess.assistance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assistance.dto.HotAssistDTO;
import com.bjike.goddess.assistance.entity.HotAssist;

/**
* 高温补助持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-14 10:52 ]
* @Description:	[ 高温补助持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface HotAssistRep extends JpaRep<HotAssist ,HotAssistDTO> { 

 }