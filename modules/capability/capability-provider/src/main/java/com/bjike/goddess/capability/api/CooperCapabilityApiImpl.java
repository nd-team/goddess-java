package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.capability.dto.CooperCapabilityDTO;
import com.bjike.goddess.capability.entity.CooperCapability;
import com.bjike.goddess.capability.service.CooperCapabilitySer;
import com.bjike.goddess.capability.to.CooperCapabilityTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合作对象商务展示业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:31 ]
 * @Description: [ 合作对象商务展示业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cooperCapabilityApiImpl")
public class CooperCapabilityApiImpl implements CooperCapabilityAPI {

    @Autowired
    private CooperCapabilitySer cooperCapabilitySer;

    @Override
    public Long counts(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {
        return cooperCapabilitySer.counts(cooperCapabilityDTO);
    }

    @Override
    public CooperCapabilityBO getOne(String id) throws SerException {
        return  cooperCapabilitySer.getOne(id);
    }

    @Override
    public List<CooperCapabilityBO> listCooperCapability(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {
        return cooperCapabilitySer.listCooperCapability(cooperCapabilityDTO);
    }

    @Override
    public CooperCapabilityBO addCooperCapability(CooperCapabilityTO cooperCapabilityTO) throws SerException {
        return cooperCapabilitySer.addCooperCapability(cooperCapabilityTO);
    }

    @Override
    public CooperCapabilityBO editCooperCapability(CooperCapabilityTO cooperCapabilityTO) throws SerException {
        return cooperCapabilitySer.editCooperCapability(cooperCapabilityTO);
    }

    @Override
    public void deleteCooperCapability(String id) throws SerException {
        cooperCapabilitySer.deleteCooperCapability(id);
    }

    @Override
    public CooperCapabilityBO getCompanyConnector(String id) throws SerException {
        return cooperCapabilitySer.getCompanyConnector(id);
    }


    @Override
    public CooperCapabilityBO editCompanyConnector(CooperCapabilityTO cooperCapabilityTO) throws SerException {
        return cooperCapabilitySer.editCompanyConnector( cooperCapabilityTO );
    }

    @Override
    public List<CooperCapabilityBO> listCooperCapabilityByName(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {
        return cooperCapabilitySer.listCooperCapabilityByName( cooperCapabilityDTO );
    }

    @Override
    public CooperCapabilityBO getCompany(String companyName) throws SerException {
        return cooperCapabilitySer.getCompany(companyName);
    }

    @Override
    public List<String> listAllCompanyName() throws SerException {
        return cooperCapabilitySer.listAllCompanyName();
    }

    @Override
    public List<CooperCapabilityBO> listCompanyContact(String companyName) throws SerException {
        return cooperCapabilitySer.listCompanyContact(companyName);
    }

    @Override
    public byte[] exportExcel(String companyName) throws SerException {
        return cooperCapabilitySer.exportExcel(companyName);
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return cooperCapabilitySer.guidePermission( guidePermissionTO );
    }

    @Override
    public CooperCapabilityBO importExcel(List<CooperCapabilityTO> cooperCapabilityTO) throws SerException {
        return cooperCapabilitySer.importExcel( cooperCapabilityTO );
    }

    @Override
    public byte[] templateExport( ) throws SerException {
        return cooperCapabilitySer.templateExport(   );
    }
}