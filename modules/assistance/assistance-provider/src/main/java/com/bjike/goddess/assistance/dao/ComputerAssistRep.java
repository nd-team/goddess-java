package com.bjike.goddess.assistance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.assistance.dto.ComputerAssistDTO;
import com.bjike.goddess.assistance.entity.ComputerAssist;

/**
* 电脑补助持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-14 10:20 ]
* @Description:	[ 电脑补助持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ComputerAssistRep extends JpaRep<ComputerAssist ,ComputerAssistDTO> { 

 }