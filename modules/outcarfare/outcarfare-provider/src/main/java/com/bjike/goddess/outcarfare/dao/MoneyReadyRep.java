package com.bjike.goddess.outcarfare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.outcarfare.dto.MoneyReadyDTO;
import com.bjike.goddess.outcarfare.entity.MoneyReady;

/**
* 资金准备审核持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-05 02:41 ]
* @Description:	[ 资金准备审核持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MoneyReadyRep extends JpaRep<MoneyReady ,MoneyReadyDTO> { 

 }