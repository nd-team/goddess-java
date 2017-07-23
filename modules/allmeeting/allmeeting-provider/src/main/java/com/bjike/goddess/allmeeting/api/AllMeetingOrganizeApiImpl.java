package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.AllMeetingOrganizeBO;
import com.bjike.goddess.allmeeting.bo.MeetingLayBO;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.excel.SonPermissionObject;
import com.bjike.goddess.allmeeting.service.AllMeetingOrganizeSer;
import com.bjike.goddess.allmeeting.to.AllMeetingOrganizeTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 所有工作内容汇总会议组织内容业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("allMeetingOrganizeApiImpl")
public class AllMeetingOrganizeApiImpl implements AllMeetingOrganizeAPI {


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return allMeetingOrganizeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return allMeetingOrganizeSer.guidePermission(guidePermissionTO);
    }

    @Autowired
    private AllMeetingOrganizeSer allMeetingOrganizeSer;

    @Override
    public AllMeetingOrganizeBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(allMeetingOrganizeSer.findById(id),AllMeetingOrganizeBO.class);
    }

    @Override
    public Long count(AllMeetingOrganizeDTO dto) throws SerException {
        return allMeetingOrganizeSer.count(dto);
    }

    @Override
    public AllMeetingOrganizeBO add(AllMeetingOrganizeTO to) throws SerException {
        return allMeetingOrganizeSer.insertModel(to);
    }

    @Override
    public AllMeetingOrganizeBO edit(AllMeetingOrganizeTO to) throws SerException {
        return allMeetingOrganizeSer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        allMeetingOrganizeSer.freeze(id);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        allMeetingOrganizeSer.unfreeze(id);
    }

    @Override
    public List<AllMeetingOrganizeBO> pageList(AllMeetingOrganizeDTO dto) throws SerException {
        return allMeetingOrganizeSer.pageList(dto);
    }

    @Override
    public String[] getPlanPeople() throws SerException {
        return allMeetingOrganizeSer.getPlanPeople();
    }
}