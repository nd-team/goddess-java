package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.service.CompanyFestivalTimeSer;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司放假时间安排业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyFestivalTimeApiImpl")
public class CompanyFestivalTimeApiImpl implements CompanyFestivalTimeAPI {

    @Autowired
    private CompanyFestivalTimeSer companyFestivalTimeSer;

    @Override
    public Long countCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        return companyFestivalTimeSer.countCompanyFestivalTime(companyFestivalTimeDTO);
    }

    @Override
    public List<CompanyFestivalTimeBO> listCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        return companyFestivalTimeSer.listCompanyFestivalTime(companyFestivalTimeDTO);
    }

    @Override
    public CompanyFestivalTimeBO addCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException {
        return companyFestivalTimeSer.addCompanyFestivalTime(companyFestivalTimeTO);
    }

    @Override
    public CompanyFestivalTimeBO editCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException {
        return companyFestivalTimeSer.editCompanyFestivalTime(companyFestivalTimeTO);
    }

    @Override
    public void deleteCompanyFestivalTime(String id) throws SerException {
        companyFestivalTimeSer.deleteCompanyFestivalTime(id);
    }

    @Override
    public List<String> listFestivalName() throws SerException {
        return companyFestivalTimeSer.listFestivalName();
    }

    @Override
    public CompanyFestivalTimeBO getCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        return companyFestivalTimeSer.getCompanyFestivalTime(companyFestivalTimeDTO);
    }
}