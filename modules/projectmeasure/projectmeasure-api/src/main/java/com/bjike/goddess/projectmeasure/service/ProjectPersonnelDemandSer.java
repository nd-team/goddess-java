package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmeasure.bo.ProjectPersonnelDemandBO;
import com.bjike.goddess.projectmeasure.entity.ProjectPersonnelDemand;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.to.ProjectPersonnelDemandTO;

import java.util.List;

/**
 * 项目人员需求业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectPersonnelDemandSer extends Ser<ProjectPersonnelDemand, ProjectPersonnelDemandDTO> {

    /**
     * 分页查询项目人员需求
     *
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    List<ProjectPersonnelDemandBO> list(ProjectPersonnelDemandDTO dto) throws SerException;

    /**
     * 保存项目人员需求
     *
     * @param to 项目人员需求to
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    ProjectPersonnelDemandBO save(ProjectPersonnelDemandTO to) throws SerException;

    /**
     * 根据id删除项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新项目人员需求
     *
     * @param to 项目人员需求to
     * @throws SerException
     */
    void update(ProjectPersonnelDemandTO to) throws SerException;

}