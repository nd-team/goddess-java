package com.bjike.goddess.negotiatemeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.negotiatemeeting.dto.SummaryFeedbackDTO;
import com.bjike.goddess.negotiatemeeting.entity.SummaryFeedback;

/**
* 纪要反馈投诉持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-31 03:54 ]
* @Description:	[ 纪要反馈投诉持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SummaryFeedbackRep extends JpaRep<SummaryFeedback ,SummaryFeedbackDTO> { 

 }