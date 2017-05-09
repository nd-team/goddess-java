package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectCostStatusBO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectCostStatus;
import com.bjike.goddess.projectmeasure.to.ProjectBasicInfoTO;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目费用情况业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectCostStatusSerImpl extends ServiceImpl<ProjectCostStatus, ProjectCostStatusDTO> implements ProjectCostStatusSer {

    /**
     * 分页查询项目费用情况
     *
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    @Override
    public List<ProjectCostStatusBO> list(ProjectCostStatusDTO dto) throws SerException {
        dto.getSorts().add("projectName=asc");
        List<ProjectCostStatus> list = super.findByPage(dto);
        List<ProjectCostStatusBO> listBO = BeanTransform.copyProperties(list, ProjectCostStatusBO.class);
        return listBO;
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
        ProjectCostStatus entity = BeanTransform.copyProperties(to, ProjectCostStatus.class, true);
        entity = super.save(entity);
        ProjectCostStatusBO bo = BeanTransform.copyProperties(entity, ProjectCostStatusBO.class);
        return bo;
    }

    /**
     * 更新项目费用情况
     *
     * @param to 项目费用情况to
     * @throws SerException
     */
    @Override
    public void update(ProjectCostStatusTO to) throws SerException {
        ProjectCostStatus entity = BeanTransform.copyProperties(to, ProjectCostStatus.class, true);
        super.update(entity);
    }

    /**
     * 根据id删除项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}