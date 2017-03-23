package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;

import java.util.List;

/**
 * 项目外包洽谈业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.803 ]
 * @Description: [ 项目外包洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectOutsourcingSer extends Ser<ProjectOutsourcing, ProjectOutsourcingDTO> {


    ProjectOutsourcingBO saveProjectOutsourcing(ProjectOutsourcingTO to) throws SerException;

    ProjectOutsourcingBO editProjectOutsourcing(ProjectOutsourcingTO to) throws SerException;

    List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException;

    List<ProjectOutsourcingBO> collect(ProjectOutsourcingDTO dto) throws SerException;

    void setCollectSend(QuartzCycleType cycle) throws SerException;
}