package com.bjike.goddess.attendance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.attendance.dto.OverWorkCountSetDTO;
import com.bjike.goddess.attendance.entity.OverWorkCountSet;

/**
* 加班汇总通报设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-11-03 04:16 ]
* @Description:	[ 加班汇总通报设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OverWorkCountSetRep extends JpaRep<OverWorkCountSet ,OverWorkCountSetDTO> { 

 }