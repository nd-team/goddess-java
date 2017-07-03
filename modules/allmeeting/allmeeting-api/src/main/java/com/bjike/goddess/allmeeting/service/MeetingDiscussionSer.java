package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.allmeeting.to.FirstDiscussionTO;
import com.bjike.goddess.allmeeting.to.SecondDiscussionTO;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.allmeeting.entity.MeetingDiscussion;
import com.bjike.goddess.allmeeting.dto.MeetingDiscussionDTO;

import java.util.List;

/**
* 会议讨论意见业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-05 03:10 ]
* @Description:	[ 会议讨论意见业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetingDiscussionSer extends Ser<MeetingDiscussion, MeetingDiscussionDTO> {

    List<MeetingDiscussionBO> listBySummaryId(String id) throws SerException;

    MeetingDiscussionBO addFrist(FirstDiscussionTO to) throws SerException;

    MeetingDiscussionBO addSecond(SecondDiscussionTO to) throws SerException;

    MeetingDiscussionBO addFirstByCon(MeetingDiscussionTO to) throws SerException;
}