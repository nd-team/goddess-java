package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.staffmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.staffmeeting.service.MeetingTopicSer;
import com.bjike.goddess.staffmeeting.to.MeetingTopicTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 议题管理业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:31 ]
 * @Description: [ 议题管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingTopicApiImpl")
public class MeetingTopicApiImpl implements MeetingTopicAPI {
    @Autowired
    private MeetingTopicSer meetingTopicSer;

    @Override
    public MeetingTopicBO add(MeetingTopicTO to) throws SerException {
        return meetingTopicSer.insertModel(to);
    }

    @Override
    public MeetingTopicBO edit(MeetingTopicTO to) throws SerException {
        return meetingTopicSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        meetingTopicSer.remove(id);
    }

    @Override
    public List<MeetingTopicBO> pageList(MeetingTopicDTO dto) throws SerException {
        return meetingTopicSer.pageList(dto);
    }

    @Override
    public Long count(MeetingTopicDTO dto) throws SerException {
        return meetingTopicSer.count(dto);
    }

    @Override
    public MeetingTopicBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(meetingTopicSer.findById(id), MeetingTopicBO.class);
    }
}