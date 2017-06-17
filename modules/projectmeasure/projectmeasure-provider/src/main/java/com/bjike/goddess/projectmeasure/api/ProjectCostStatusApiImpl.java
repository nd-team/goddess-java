package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectCostStatusBO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectCostStatus;
import com.bjike.goddess.projectmeasure.service.ProjectCostStatusSer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目费用情况业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectCostStatusApiImpl")
public class ProjectCostStatusApiImpl implements ProjectCostStatusAPI {

    @Autowired
    private ProjectCostStatusSer projectCostStatusSer;

    /**
     * 根据id查询项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    @Override
    public ProjectCostStatusBO findById(String id) throws SerException {
        ProjectCostStatus model = projectCostStatusSer.findById(id);
        return BeanTransform.copyProperties(model, ProjectCostStatusBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 项目费用情况dto
     * @throws SerException
     */
    @Override
    public Long count(ProjectCostStatusDTO dto) throws SerException {
        return projectCostStatusSer.count(dto);
    }

    /**
     * 分页查询项目费用情况
     *
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    @Override
    public List<ProjectCostStatusBO> list(ProjectCostStatusDTO dto) throws SerException {
        return projectCostStatusSer.list(dto);
    }

    /**
     * 保存项目费用情况
     *
     * @param to 项目费用情况to
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    @Override
    public ProjectCostStatusBO save(ProjectCostStatusTO to) throws SerException {
        return projectCostStatusSer.save(to);
    }

    /**
     * 根据id删除项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        projectCostStatusSer.remove(id);
    }

    /**
     * 更新项目费用情况
     *
     * @param to 项目费用情况to
     * @throws SerException
     */
    @Override
    public void update(ProjectCostStatusTO to) throws SerException {
        projectCostStatusSer.update(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return projectCostStatusSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectCostStatusSer.guidePermission(guidePermissionTO);
    }
}