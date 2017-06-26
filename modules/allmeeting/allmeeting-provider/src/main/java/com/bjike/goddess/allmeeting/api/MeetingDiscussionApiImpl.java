package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.allmeeting.service.MeetingDiscussionSer;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议讨论意见业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingDiscussionApiImpl")
public class MeetingDiscussionApiImpl implements MeetingDiscussionAPI {

    @Autowired
    private MeetingDiscussionSer meetingDiscussionSer;

    @Override
    public MeetingDiscussionBO addByCon(MeetingDiscussionTO to) throws SerException {
        return meetingDiscussionSer.addByCon(to);
    }

    @Override
    public MeetingDiscussionBO addByMulti(MeetingDiscussionTO to) throws SerException {
        return meetingDiscussionSer.addByMulti(to);
    }

    @Override
    public List<MeetingDiscussionBO> listBySummaryId(String id) throws SerException {
        return meetingDiscussionSer.listBySummaryId(id);
    }
}