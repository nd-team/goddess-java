package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.staffmeeting.service.MeetingDiscussionSer;
import com.bjike.goddess.staffmeeting.to.MeetingDiscussionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通告反馈投诉业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 通告反馈投诉业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingDiscussionApiImpl")
public class MeetingDiscussionApiImpl implements MeetingDiscussionAPI {

    @Autowired
    private MeetingDiscussionSer meetingDiscussionSer;



    @Override
    public MeetingDiscussionBO add(MeetingDiscussionTO to) throws SerException {
        return meetingDiscussionSer.insertModel(to);
    }

    @Override
    public List<MeetingDiscussionBO> listBySummaryId(String summaryId) throws SerException {
        return meetingDiscussionSer.listBySummaryId(summaryId);
    }

    @Override
    public MeetingDiscussionBO discussFind(String summaryId) throws SerException {
        return meetingDiscussionSer.discussFind(summaryId);
    }
}