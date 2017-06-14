package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.service.ProjectContractSer;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ExportExcelTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目承包洽谈业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.934 ]
 * @Description: [ 项目承包洽谈业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectContractApiImpl")
public class ProjectContractApiImpl implements ProjectContractAPI {

    @Autowired
    private ProjectContractSer projectContractSer;

    @Override
    public ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException {
        return projectContractSer.saveProjectContract(to);
    }

    @Override
    public ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException {
        return projectContractSer.editProjectContract(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectContractSer.remove(id);
    }

    @Override
    public List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException {
        return projectContractSer.pageList(dto);
    }

    @Override
    public List<ProjectContractCollectBO> collect(CollectConditionTO to) throws SerException {
        return projectContractSer.collect(to);
    }

    @Override
    public void setCollectSend(QuartzCycleType cycle) throws SerException {
        projectContractSer.setCollectSend(cycle);
    }

    @Override
    public ProjectContractBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(projectContractSer.findById(id), ProjectContractBO.class);
    }

    @Override
    public Long count(ProjectContractDTO dto) throws SerException {
        return projectContractSer.count(dto);
    }

    @Override
    public void leadExcel(List<ProjectContractTO> toList) throws SerException {
        projectContractSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(String contractInProject, String startDate, String endDate) throws SerException {
        return projectContractSer.exportExcel(contractInProject, startDate, endDate);
    }

    @Override
    public List<ProjectContractBO> projects() throws SerException {
        return projectContractSer.projects();
    }
}