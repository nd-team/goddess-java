package com.bjike.goddess.democraticmeet.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.democraticmeet.dto.MeetDesignDTO;
import com.bjike.goddess.democraticmeet.entity.MeetDesign;

/**
* 会议组织部分内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-02 11:27 ]
* @Description:	[ 会议组织部分内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetDesignRep extends JpaRep<MeetDesign ,MeetDesignDTO> { 

 }