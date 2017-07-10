package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingOrganizeBO;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.service.MeetingOrganizeSer;
import com.bjike.goddess.staffmeeting.to.MeetingOrganizeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工代表大会组织内容业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("meetingOrganizeApiImpl")
public class MeetingOrganizeApiImpl implements MeetingOrganizeAPI {

    @Autowired
    private MeetingOrganizeSer meetingOrganizeSer;

    @Override
    public Long count(MeetingOrganizeDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        return meetingOrganizeSer.count(dto);
    }

    @Override
    public MeetingOrganizeBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(meetingOrganizeSer.findById(id),MeetingOrganizeBO.class);
    }

    @Override
    public MeetingOrganizeBO add(MeetingOrganizeTO to) throws SerException {
        return meetingOrganizeSer.insertModel(to);
    }

    @Override
    public MeetingOrganizeBO edit(MeetingOrganizeTO to) throws SerException {
        return meetingOrganizeSer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        meetingOrganizeSer.freeze(id);
    }

    @Override
    public List<MeetingOrganizeBO> pageList(MeetingOrganizeDTO dto) throws SerException {
        return meetingOrganizeSer.pageList(dto);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        meetingOrganizeSer.unfreeze(id);
    }
}