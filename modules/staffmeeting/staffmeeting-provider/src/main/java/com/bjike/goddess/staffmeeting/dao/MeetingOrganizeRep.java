package com.bjike.goddess.staffmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;

/**
* 员工代表大会组织内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-06 10:45 ]
* @Description:	[ 员工代表大会组织内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetingOrganizeRep extends JpaRep<MeetingOrganize ,MeetingOrganizeDTO> { 

 }