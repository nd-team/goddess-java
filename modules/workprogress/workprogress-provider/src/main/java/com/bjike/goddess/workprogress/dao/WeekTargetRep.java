package com.bjike.goddess.workprogress.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workprogress.dto.WeekTargetDTO;
import com.bjike.goddess.workprogress.entity.WeekTarget;

/**
* 周指标持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-17 03:13 ]
* @Description:	[ 周指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WeekTargetRep extends JpaRep<WeekTarget ,WeekTargetDTO> { 

 }