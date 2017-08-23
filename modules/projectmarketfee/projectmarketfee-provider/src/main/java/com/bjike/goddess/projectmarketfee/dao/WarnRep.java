package com.bjike.goddess.projectmarketfee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectmarketfee.dto.WarnDTO;
import com.bjike.goddess.projectmarketfee.entity.Warn;

/**
* 预警设计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-09 04:53 ]
* @Description:	[ 预警设计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WarnRep extends JpaRep<Warn ,WarnDTO> { 

 }