package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.allmeeting.dto.MeetingDiscussionDTO;
import com.bjike.goddess.allmeeting.service.MeetingDiscussionSer;
import com.bjike.goddess.allmeeting.to.FirstDiscussionTO;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.allmeeting.to.SecondDiscussionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
    public List<MeetingDiscussionBO> listBySummaryId(String id) throws SerException {
        return meetingDiscussionSer.listBySummaryId(id);
    }

    @Override
    public MeetingDiscussionBO addFirst(FirstDiscussionTO to) throws SerException {
        return meetingDiscussionSer.addFrist(to);
    }

    @Override
    public MeetingDiscussionBO addSecond(SecondDiscussionTO to) throws SerException {
        return meetingDiscussionSer.addSecond(to);
    }

    @Override
    public MeetingDiscussionBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(meetingDiscussionSer.findById(id), MeetingDiscussionBO.class);
    }

    @Override
    public Long count(MeetingDiscussionDTO dto) throws SerException {
        return meetingDiscussionSer.count(dto);
    }

    @Override
    public MeetingDiscussionBO addFirstByCon(MeetingDiscussionTO to) throws SerException {
        return meetingDiscussionSer.addFirstByCon(to);
    }

}