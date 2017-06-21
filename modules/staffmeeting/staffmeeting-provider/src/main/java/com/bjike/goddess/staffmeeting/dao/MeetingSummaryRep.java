package com.bjike.goddess.staffmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;

/**
* 员工代表大会纪要持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-06 11:33 ]
* @Description:	[ 员工代表大会纪要持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetingSummaryRep extends JpaRep<MeetingSummary ,MeetingSummaryDTO> { 

 }