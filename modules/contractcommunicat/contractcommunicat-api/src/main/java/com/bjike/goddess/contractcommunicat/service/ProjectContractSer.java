package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;

import java.util.List;

/**
 * 项目承包洽谈业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.916 ]
 * @Description: [ 项目承包洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectContractSer extends Ser<ProjectContract, ProjectContractDTO> {

    ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException;

    ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException;

    List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException;

    List<ProjectContractBO> collect(ProjectContractDTO dto) throws SerException;

    void setCollectSend(QuartzCycleType cycle) throws SerException;
}