package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.PayDetailDTO;
import com.bjike.goddess.secure.entity.PayDetail;

/**
* 缴费比例明细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-04-25 12:55 ]
* @Description:	[ 缴费比例明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PayDetailRep extends JpaRep<PayDetail ,PayDetailDTO> { 

 }