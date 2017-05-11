package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.ProjectContractBO;
import com.bjike.goddess.contractware.dto.ProjectContractDTO;
import com.bjike.goddess.contractware.to.ProjectContractTO;

import java.util.List;

/**
 * 项目合同业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectContractAPI {

    /**
     * 项目合同列表总条数
     */
    default Long countProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        return null;
    }

    /**
     * 一个项目合同
     *
     * @return class ProjectContractBO
     */
    default ProjectContractBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 项目合同
     *
     * @param projectContractDTO 项目合同dto
     * @return class ProjectContractBO
     * @throws SerException
     */
    default List<ProjectContractBO> findListProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        return null;
    }

    /**
     * 添加项目合同
     *
     * @param projectContractTO 项目合同数据to
     * @return class ProjectContractBO
     * @throws SerException
     */
    default ProjectContractBO insertProjectContract(ProjectContractTO projectContractTO) throws SerException {
        return null;
    }

    /**
     * 编辑项目合同
     *
     * @param projectContractTO 项目合同数据to
     * @return class ProjectContractBO
     * @throws SerException
     */
    default ProjectContractBO editProjectContract(ProjectContractTO projectContractTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除项目合同
     *
     * @param id
     * @throws SerException
     */
    default void removeProjectContract(String id) throws SerException {

    }

}