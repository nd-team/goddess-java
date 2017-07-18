package com.bjike.goddess.announcement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.announcement.dto.AnnouncementDTO;
import com.bjike.goddess.announcement.entity.Announcement;

/**
* 公告持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-07 02:37 ]
* @Description:	[ 公告持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AnnouncementRep extends JpaRep<Announcement ,AnnouncementDTO> { 

 }