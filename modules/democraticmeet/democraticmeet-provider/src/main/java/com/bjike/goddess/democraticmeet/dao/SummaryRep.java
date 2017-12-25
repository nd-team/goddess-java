package com.bjike.goddess.democraticmeet.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.democraticmeet.dto.SummaryDTO;
import com.bjike.goddess.democraticmeet.entity.Summary;

/**
* 会议纪要持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-03 11:22 ]
* @Description:	[ 会议纪要持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SummaryRep extends JpaRep<Summary ,SummaryDTO> { 

 }