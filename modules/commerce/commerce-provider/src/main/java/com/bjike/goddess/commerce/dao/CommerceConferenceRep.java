package com.bjike.goddess.commerce.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.commerce.dto.CommerceConferenceDTO;
import com.bjike.goddess.commerce.entity.CommerceConference;

/**
* 商务会议持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-06-02 10:36 ]
* @Description:	[ 商务会议持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CommerceConferenceRep extends JpaRep<CommerceConference ,CommerceConferenceDTO> { 

 }