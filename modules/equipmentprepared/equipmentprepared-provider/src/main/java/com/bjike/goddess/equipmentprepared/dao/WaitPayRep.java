package com.bjike.goddess.equipmentprepared.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.equipmentprepared.dto.WaitPayDTO;
import com.bjike.goddess.equipmentprepared.entity.WaitPay;

/**
* 等待付款持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-13 02:27 ]
* @Description:	[ 等待付款持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WaitPayRep extends JpaRep<WaitPay ,WaitPayDTO> { 

 }