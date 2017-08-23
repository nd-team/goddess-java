package com.bjike.goddess.announcement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.announcement.dto.AnnouncementUserDTO;
import com.bjike.goddess.announcement.entity.AnnouncementUser;

/**
* 公告对应的发布对象持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-12 10:34 ]
* @Description:	[ 公告对应的发布对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AnnouncementUserRep extends JpaRep<AnnouncementUser ,AnnouncementUserDTO> { 

 }