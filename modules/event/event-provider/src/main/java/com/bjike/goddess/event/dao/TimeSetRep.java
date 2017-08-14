package com.bjike.goddess.event.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.event.dto.TimeSetDTO;
import com.bjike.goddess.event.entity.TimeSet;

/**
* 提醒间隔时间设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-09 04:11 ]
* @Description:	[ 提醒间隔时间设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TimeSetRep extends JpaRep<TimeSet ,TimeSetDTO> { 

 }