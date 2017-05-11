package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.ProjectContractBO;
import com.bjike.goddess.contractware.dto.ProjectContractDTO;
import com.bjike.goddess.contractware.entity.ProjectContract;
import com.bjike.goddess.contractware.service.ProjectContractSer;
import com.bjike.goddess.contractware.to.ProjectContractTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目合同业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectContractApiImpl")
public class ProjectContractApiImpl implements ProjectContractAPI {

    @Autowired
    private ProjectContractSer projectContractSer;
    @Override
    public Long countProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        return projectContractSer.countProjectContract(projectContractDTO);
    }

    @Override
    public ProjectContractBO getOne(String id) throws SerException {
        return projectContractSer.getOne(id);
    }

    @Override
    public List<ProjectContractBO> findListProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        return projectContractSer.findListProjectContract(projectContractDTO);
    }

    @Override
    public ProjectContractBO insertProjectContract(ProjectContractTO projectContractTO) throws SerException {
        return projectContractSer.insertProjectContract(projectContractTO);
    }

    @Override
    public ProjectContractBO editProjectContract(ProjectContractTO projectContractTO) throws SerException {
        return projectContractSer.editProjectContract(projectContractTO);
    }

    @Override
    public void removeProjectContract(String id) throws SerException {
        projectContractSer.removeProjectContract(id);
    }

}