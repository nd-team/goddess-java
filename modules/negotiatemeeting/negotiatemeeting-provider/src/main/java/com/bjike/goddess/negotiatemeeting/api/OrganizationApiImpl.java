package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.OrganizationBO;
import com.bjike.goddess.negotiatemeeting.dto.OrganizationDTO;
import com.bjike.goddess.negotiatemeeting.service.OrganizationSer;
import com.bjike.goddess.negotiatemeeting.to.OrganizationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 协商会议组织内容业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:30 ]
 * @Description: [ 协商会议组织内容业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("organizationApiImpl")
public class OrganizationApiImpl implements OrganizationAPI {
    @Autowired
    private OrganizationSer organizationSer;

    @Override
    public OrganizationBO save(OrganizationTO to) throws SerException {
        return organizationSer.save(to);
    }

    @Override
    public void edit(OrganizationTO to) throws SerException {
        organizationSer.edit(to);
    }

    @Override
    public List<OrganizationBO> list(OrganizationDTO dto) throws SerException {
        return organizationSer.list(dto);
    }

    @Override
    public OrganizationBO findByID(String id) throws SerException {
        return organizationSer.findByID(id);
    }

    @Override
    public Long countNum(OrganizationDTO dto) throws SerException {
        return organizationSer.countNum(dto);
    }

    @Override
    public List<String> findPeople(String[] positionId) throws SerException {
        return organizationSer.findPeople(positionId);
    }

    @Override
    public Set<String> allMeetingsNumber() throws SerException {
        return organizationSer.allMeetingsNumber();
    }

    @Override
    public Set<String> allMeetingFormats() throws SerException {
        return organizationSer.allMeetingFormats();
    }

    @Override
    public Set<String> findHosts(String meetingLevel) throws SerException {
        return organizationSer.findHosts(meetingLevel);
    }
}