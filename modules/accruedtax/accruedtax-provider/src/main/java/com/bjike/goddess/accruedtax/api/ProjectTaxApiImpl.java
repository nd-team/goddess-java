package com.bjike.goddess.accruedtax.api;

import com.bjike.goddess.accruedtax.bo.ProjectTaxBO;
import com.bjike.goddess.accruedtax.dto.ProjectTaxDTO;
import com.bjike.goddess.accruedtax.service.ProjectTaxSer;
import com.bjike.goddess.accruedtax.to.ProjectTaxTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目上税金业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:18 ]
 * @Description: [ 项目上税金业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectTaxApiImpl")
public class ProjectTaxApiImpl implements ProjectTaxAPI {


    @Autowired
    private ProjectTaxSer projectTaxSer;

    @Override
    public Long countProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        return projectTaxSer.countProjectTax( projectTaxDTO);
    }

    @Override
    public ProjectTaxBO getOneById(String id) throws SerException {
        return projectTaxSer.getOneById(id);
    }

    @Override
    public List<ProjectTaxBO> listProjectTax(ProjectTaxDTO projectTaxDTO) throws SerException {
        return projectTaxSer.listProjectTax( projectTaxDTO );
    }

    @Override
    public ProjectTaxBO addProjectTax(ProjectTaxTO projectTaxTO) throws SerException {
        return projectTaxSer.addProjectTax( projectTaxTO );
    }

    @Override
    public ProjectTaxBO editProjectTax(ProjectTaxTO projectTaxTO) throws SerException {
        return projectTaxSer.editProjectTax( projectTaxTO );
    }

    @Override
    public void deleteProjectTax(String id) throws SerException {
        projectTaxSer.deleteProjectTax( id );
    }

    @Override
    public List<ProjectTaxBO> collectCompany(ProjectTaxDTO projectTaxDTO) throws SerException {
        return projectTaxSer.collectCompany( projectTaxDTO );
    }

    @Override
    public List<ProjectTaxBO> collectTaxType(ProjectTaxDTO projectTaxDTO) throws SerException {
        return projectTaxSer.collectTaxType( projectTaxDTO);
    }

    @Override
    public List<String> listProject() throws SerException {
        return projectTaxSer.listProject();
    }

    @Override
    public List<String> listTaxType() throws SerException {
        return projectTaxSer.listTaxType();
    }

}