package com.bjike.goddess.allmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.allmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.allmeeting.entity.MeetingTopic;

/**
* 议题管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-05-24 04:49 ]
* @Description:	[ 议题管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetingTopicRep extends JpaRep<MeetingTopic ,MeetingTopicDTO> { 

 }