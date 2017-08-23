package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompanyProjectBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CompanyProjectDTO;
import com.bjike.goddess.capability.entity.CompanyProject;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司已完成项目数业务实现
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:20 ]
 * @Description: [ 公司已完成项目数业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CompanyProjectSerImpl extends ServiceImpl<CompanyProject, CompanyProjectDTO> implements CompanyProjectSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyProjectBO addCompanyProject(String[] companyProjects, String companyId) throws SerException {
        List<CompanyProject> companyProjectList = new ArrayList<>();
        if (companyProjects != null && companyProjects.length > 0) {
            for (String str : companyProjects) {
                CompanyProject companyProject = new CompanyProject();
                companyProject.setName(str);
                companyProject.setBaseId(companyId);
                companyProjectList.add(companyProject);
            }
        }
        if (companyProjectList != null && companyProjectList.size() > 0) {
            super.save(companyProjectList);
        }
        return new CompanyProjectBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyProjectBO editCompanyProject(String[] companyProjects, String companyId) throws SerException {

        List<CompanyProject> companyProjectList = new ArrayList<CompanyProject>();
        if (null != companyProjects && companyProjects.length > 0) {
            this.deleteCompanyProject(companyId);
            for (String str : companyProjects) {
                CompanyProject companyProject = new CompanyProject();
                companyProject.setBaseId(companyId);
                companyProject.setName(str);
                companyProjectList.add(companyProject);
            }
            if (null != companyProjectList && companyProjectList.size() > 0) {
                super.save(companyProjectList);
            }

        }
        return new CompanyProjectBO();
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompanyProject(String id) throws SerException {
        CompanyProjectDTO dto = new CompanyProjectDTO();
        dto.getConditions().add(Restrict.eq("baseId", id));
        List<CompanyProject> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            super.remove(list);
        }
    }
}