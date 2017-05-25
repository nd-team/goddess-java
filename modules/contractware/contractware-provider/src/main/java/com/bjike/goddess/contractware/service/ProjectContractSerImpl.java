package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.ProjectContractBO;
import com.bjike.goddess.contractware.dto.ProjectContractDTO;
import com.bjike.goddess.contractware.entity.ProjectContract;
import com.bjike.goddess.contractware.to.ProjectContractTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目合同业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class ProjectContractSerImpl extends ServiceImpl<ProjectContract, ProjectContractDTO> implements ProjectContractSer {

    @Override
    public Long countProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        Long count = super.count(projectContractDTO);
        return count;
    }

    @Override
    public ProjectContractBO getOne(String id) throws SerException {
        ProjectContract projectContract = super.findById(id);
        return BeanTransform.copyProperties(projectContract,ProjectContractBO.class);
    }

    @Override
    public List<ProjectContractBO> findListProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        List<ProjectContract> projectContracts = super.findByPage(projectContractDTO);
        List<ProjectContractBO> projectContractBOS = BeanTransform.copyProperties(projectContracts,ProjectContractBO.class);
        return projectContractBOS;
    }

    @Override
    public ProjectContractBO insertProjectContract(ProjectContractTO projectContractTO) throws SerException {
        ProjectContract projectContract = BeanTransform.copyProperties(projectContractTO,ProjectContract.class,true);
        projectContract.setCreateTime(LocalDateTime.now());
        super.save(projectContract);
        return BeanTransform.copyProperties(projectContract,ProjectContractBO.class);
    }

    @Override
    public ProjectContractBO editProjectContract(ProjectContractTO projectContractTO) throws SerException {
        ProjectContract projectContract = super.findById(projectContractTO.getId());
        BeanTransform.copyProperties(projectContractTO,projectContract,true);
        projectContract.setModifyTime(LocalDateTime.now());
        super.update(projectContract);
        return BeanTransform.copyProperties(projectContract,ProjectContractBO.class);
    }

    @Override
    public void removeProjectContract(String id) throws SerException {
        super.remove(id);
    }

}