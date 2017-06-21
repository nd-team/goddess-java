package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
* 会议讨论意见业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-05 03:10 ]
* @Description:	[ 会议讨论意见业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MeetingDiscussionAPI  {

    MeetingDiscussionBO addByCon(MeetingDiscussionTO to) throws SerException;

    MeetingDiscussionBO addByMulti(MeetingDiscussionTO to) throws SerException;

    List<MeetingDiscussionBO> listBySummaryId(String id) throws SerException;
}