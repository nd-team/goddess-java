package com.bjike.goddess.businessinteraction.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;

/**
* 洽谈详情持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-28 03:27 ]
* @Description:	[ 洽谈详情持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TalkDetailRep extends JpaRep<TalkDetail ,TalkDetailDTO> { 

 }