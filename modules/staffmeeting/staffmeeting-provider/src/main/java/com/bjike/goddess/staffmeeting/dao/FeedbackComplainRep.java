package com.bjike.goddess.staffmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.entity.FeedbackComplain;

/**
* 通告反馈投诉持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-06 04:23 ]
* @Description:	[ 通告反馈投诉持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FeedbackComplainRep extends JpaRep<FeedbackComplain ,FeedbackComplainDTO> { 

 }