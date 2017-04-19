package com.bjike.goddess.assistance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.entity.AgeAssist;

/**
* 工龄补助持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-14 10:59 ]
* @Description:	[ 工龄补助持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AgeAssistRep extends JpaRep<AgeAssist ,AgeAssistDTO> { 

 }