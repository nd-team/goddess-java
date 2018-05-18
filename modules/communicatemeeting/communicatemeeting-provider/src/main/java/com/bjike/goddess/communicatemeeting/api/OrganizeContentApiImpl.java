package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.OrganizeContentBO;
import com.bjike.goddess.communicatemeeting.dto.OrganizeContentDTO;
import com.bjike.goddess.communicatemeeting.service.OrganizeContentSer;
import com.bjike.goddess.communicatemeeting.to.OrganizeContentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 交流会组织内容业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:16 ]
 * @Description: [ 交流会组织内容业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("organizeContentApiImpl")
public class OrganizeContentApiImpl implements OrganizeContentAPI {
    @Autowired
    private OrganizeContentSer organizeContentSer;

    @Override
    public OrganizeContentBO save(OrganizeContentTO to) throws SerException {
        return organizeContentSer.save(to);
    }

    @Override
    public void edit(OrganizeContentTO to) throws SerException {
        organizeContentSer.edit(to);
    }

    @Override
    public List<OrganizeContentBO> list(OrganizeContentDTO dto) throws SerException {
        return organizeContentSer.list(dto);
    }

    @Override
    public OrganizeContentBO findByID(String id) throws SerException {
        return organizeContentSer.findByID(id);
    }

    @Override
    public Long countNum(OrganizeContentDTO dto) throws SerException {
        return organizeContentSer.countNum(dto);
    }

    @Override
    public void updateHost(String id, String host) throws SerException {
        organizeContentSer.updateHost(id, host);
    }

    @Override
    public Set<String> findPeoples(String... jobs) throws SerException {
        return organizeContentSer.findPeoples(jobs);
    }

    @Override
    public Set<String> allMeetingsNumber() throws SerException {
        return organizeContentSer.allMeetingsNumber();
    }

    @Override
    public Set<String> allMeetingFormats() throws SerException {
        return organizeContentSer.allMeetingFormats();
    }
}