package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.service.ProjectOutsourcingSer;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目外包洽谈业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.828 ]
 * @Description: [ 项目外包洽谈业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectOutsourcingApiImpl")
public class ProjectOutsourcingApiImpl implements ProjectOutsourcingAPI {

    @Autowired
    private ProjectOutsourcingSer projectOutsourcingSer;

    @Override
    public ProjectOutsourcingBO saveProjectOutsource(ProjectOutsourcingTO to) throws SerException {
        return projectOutsourcingSer.saveProjectOutsourcing(to);
    }

    @Override
    public ProjectOutsourcingBO editProjectOutsource(ProjectOutsourcingTO to) throws SerException {
        return projectOutsourcingSer.editProjectOutsourcing(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectOutsourcingSer.remove(id);
    }

    @Override
    public List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException {
        return projectOutsourcingSer.pageList(dto);
    }

    @Override
    public List<ProjectOutsourcingCollectBO> collect(CollectConditionTO to) throws SerException {
        return projectOutsourcingSer.collect(to);
    }

//    @Override
//    public void setCollectSend(QuartzCycleType cycleType) throws SerException {
//        projectOutsourcingSer.setCollectSend(cycleType);
//    }

    @Override
    public Long count(ProjectOutsourcingDTO dto) throws SerException {
        return projectOutsourcingSer.count(dto);
    }

    @Override
    public ProjectOutsourcingBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(projectOutsourcingSer.findById(id), ProjectOutsourcingBO.class);
    }

    @Override
    public void leadExcel(List<ProjectOutsourcingTO> toList) throws SerException {
        projectOutsourcingSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException {
        return projectOutsourcingSer.exportExcel(contractInProject, startDate, endDate);
    }

    @Override
    public List<ProjectOutsourcingBO> projects() throws SerException {
        return projectOutsourcingSer.prjects();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return projectOutsourcingSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return projectOutsourcingSer.guidePermission(to);
    }

    @Override
    public byte[] exportExcelModule() throws SerException {
        return projectOutsourcingSer.exportExcelModule();
    }
}