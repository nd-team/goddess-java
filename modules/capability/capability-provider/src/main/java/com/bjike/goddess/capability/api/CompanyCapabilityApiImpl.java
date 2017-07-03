package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.entity.CompanyCapability;
import com.bjike.goddess.capability.excele.SonPermissionObject;
import com.bjike.goddess.capability.service.CompanyCapabilitySer;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司能力展示业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyCapabilityApiImpl")
public class CompanyCapabilityApiImpl implements CompanyCapabilityAPI {

    @Autowired
    private CompanyCapabilitySer companyCapabilitySer;

    @Override
    public Long counts(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        return companyCapabilitySer.counts(companyCapabilityDTO);
    }

    @Override
    public CompanyCapabilityBO getOne(String id) throws SerException {
        return companyCapabilitySer.getOne(id);
    }

    @Override
    public List<CompanyCapabilityBO> listCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        return companyCapabilitySer.listCompanyCapability(companyCapabilityDTO);
    }

    @Override
    public CompanyCapabilityBO addCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        return companyCapabilitySer.addCompanyCapability(companyCapabilityTO);
    }

    @Override
    public CompanyCapabilityBO editCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        return companyCapabilitySer.editCompanyCapability(companyCapabilityTO);
    }

    @Override
    public void deleteCompanyCapability(String id) throws SerException {
        companyCapabilitySer.deleteCompanyCapability(id);
    }

    @Override
    public List<CompanyCapabilityBO> listCompanyCapabilityByName(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        return companyCapabilitySer.listCompanyCapabilityByName(companyCapabilityDTO );
    }


    @Override
    public List<String> listAllCompanyName() throws SerException {
        return companyCapabilitySer.listAllCompanyName();
    }

    @Override
    public byte[] exportExcel(String companyName) throws SerException {
        return companyCapabilitySer.exportExcel(companyName);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return companyCapabilitySer.sonPermission();
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return companyCapabilitySer.guidePermission( guidePermissionTO );
    }



}