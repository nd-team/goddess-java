package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.entity.SelfCapability;
import com.bjike.goddess.capability.service.SelfCapabilitySer;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人能力展示业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("selfCapabilityApiImpl")
public class SelfCapabilityApiImpl implements SelfCapabilityAPI {

    @Autowired
    private SelfCapabilitySer selfCapabilitySer;

    @Override
    public Long counts(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        return selfCapabilitySer.counts(selfCapabilityDTO);
    }

    @Override
    public SelfCapabilityBO getOne(String id) throws SerException {
        return selfCapabilitySer.getOne(id);
    }

    @Override
    public List<SelfCapabilityBO> listSelfCapability(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        return selfCapabilitySer.listSelfCapability(selfCapabilityDTO);
    }

    @Override
    public SelfCapabilityBO addSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        return selfCapabilitySer.addSelfCapability(selfCapabilityTO);
    }

    @Override
    public SelfCapabilityBO editSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        return selfCapabilitySer.editSelfCapability(selfCapabilityTO);
    }


    @Override
    public void deleteSelfCapability(String id) throws SerException {
        selfCapabilitySer.deleteSelfCapability(id);
    }



    @Override
    public List<SelfCapabilityBO> listSelfCapabilityByName(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        return selfCapabilitySer.listSelfCapabilityByName(selfCapabilityDTO);
    }

    @Override
    public SelfCapabilityBO getSelf(String name) throws SerException {
        return selfCapabilitySer.getSelf(name);
    }

    @Override
    public List<String> listAllSelfName() throws SerException {
        return selfCapabilitySer.listAllSelfName();
    }

    @Override
    public byte[] exportExcel(String name) throws SerException {
        return selfCapabilitySer.exportExcel(name);
    }
}