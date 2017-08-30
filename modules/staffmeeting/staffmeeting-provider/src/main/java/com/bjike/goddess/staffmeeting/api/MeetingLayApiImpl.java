package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingLayBO;
import com.bjike.goddess.staffmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.staffmeeting.service.MeetingLaySer;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingLayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议层面业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingLayApiImpl")
public class MeetingLayApiImpl implements MeetingLayAPI {

    @Autowired
    private MeetingLaySer meetingLaySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return meetingLaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return meetingLaySer.guidePermission(guidePermissionTO);
    }

    @Override
    public MeetingLayBO add(MeetingLayTO to) throws SerException {
        return meetingLaySer.insertModel(to);
    }

    @Override
    public MeetingLayBO edit(MeetingLayTO to) throws SerException {
        return meetingLaySer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        meetingLaySer.remove(id);
    }

    @Override
    public List<MeetingLayBO> pageList(MeetingLayDTO dto) throws SerException {
        return meetingLaySer.pageList(dto);
    }

    @Override
    public Long count(MeetingLayDTO dto) throws SerException {
        return meetingLaySer.count(dto);
    }

    @Override
    public MeetingLayBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(meetingLaySer.findById(id), MeetingLayBO.class);
    }

    @Override
    public String[] findPosition() throws SerException {
        return meetingLaySer.findPosition();
    }
}