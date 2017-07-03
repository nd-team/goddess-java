package com.bjike.goddess.staffmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingLay;

/**
* 会议层面持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-03 03:34 ]
* @Description:	[ 会议层面持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetingLayRep extends JpaRep<MeetingLay ,MeetingLayDTO> { 

 }