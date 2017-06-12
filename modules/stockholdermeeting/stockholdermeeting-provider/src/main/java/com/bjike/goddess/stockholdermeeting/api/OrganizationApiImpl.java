package com.bjike.goddess.stockholdermeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.stockholdermeeting.bo.OrganizationBO;
import com.bjike.goddess.stockholdermeeting.dto.OrganizationDTO;
import com.bjike.goddess.stockholdermeeting.service.OrganizationSer;
import com.bjike.goddess.stockholdermeeting.to.OrganizationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:46 ]
 * @Description: [ 股东大会组织内容业务接口实现 ]
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
    public List<String> findPeople(String[] positionIds) throws SerException {
        return organizationSer.findPeople(positionIds);
    }

    @Override
    public Set<String> allMeetingsNumbers() throws SerException {
        return organizationSer.allMeetingsNumbers();
    }

    @Override
    public Set<String> allMeetingFormats() throws SerException {
        return organizationSer.allMeetingFormats();
    }

    @Override
    public void freeze(String id) throws SerException {
        organizationSer.freeze(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        organizationSer.thaw(id);
    }
}