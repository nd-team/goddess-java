package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectPersonnelDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectPersonnelDemand;
import com.bjike.goddess.projectmeasure.service.ProjectPersonnelDemandSer;
import com.bjike.goddess.projectmeasure.to.ProjectPersonnelDemandTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目人员需求业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectPersonnelDemandApiImpl")
public class ProjectPersonnelDemandApiImpl implements ProjectPersonnelDemandAPI {

    @Autowired
    private ProjectPersonnelDemandSer projectPersonnelDemandSer;

    /**
     * 根据id查询项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    public ProjectPersonnelDemandBO findById(String id) throws SerException {
        ProjectPersonnelDemand model = projectPersonnelDemandSer.findById(id);
        return BeanTransform.copyProperties(model, ProjectPersonnelDemandBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 项目人员需求dto
     * @throws SerException
     */
    @Override
    public Long count(ProjectPersonnelDemandDTO dto) throws SerException {
        return projectPersonnelDemandSer.count(dto);
    }

    /**
     * 分页查询项目人员需求
     *
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    public List<ProjectPersonnelDemandBO> list(ProjectPersonnelDemandDTO dto) throws SerException {
        return projectPersonnelDemandSer.list(dto);
    }

    /**
     * 保存项目人员需求
     *
     * @param to 项目人员需求to
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    public ProjectPersonnelDemandBO save(ProjectPersonnelDemandTO to) throws SerException {
        return projectPersonnelDemandSer.save(to);
    }

    /**
     * 根据id删除项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        projectPersonnelDemandSer.remove(id);
    }

    /**
     * 更新项目人员需求
     *
     * @param to 项目人员需求to
     * @throws SerException
     */
    @Override
    public void update(ProjectPersonnelDemandTO to) throws SerException {
        projectPersonnelDemandSer.update(to);
    }
}