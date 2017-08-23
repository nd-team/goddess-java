package com.bjike.goddess.checkfunds.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.entity.RemainAdjust;

/**
* 余额调节持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-10 04:11 ]
* @Description:	[ 余额调节持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RemainAdjustRep extends JpaRep<RemainAdjust ,RemainAdjustDTO> { 

 }