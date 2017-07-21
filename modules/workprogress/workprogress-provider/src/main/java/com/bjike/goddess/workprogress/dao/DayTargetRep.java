package com.bjike.goddess.regionalprogresscollect.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.regionalprogresscollect.dto.DayTargetDTO;
import com.bjike.goddess.regionalprogresscollect.entity.DayTarget;

/**
* 日指标持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-17 03:15 ]
* @Description:	[ 日指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DayTargetRep extends JpaRep<DayTarget ,DayTargetDTO> { 

 }