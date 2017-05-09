package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectPersonnelDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectPersonnelDemand;
import com.bjike.goddess.projectmeasure.to.ProjectPersonnelDemandTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目人员需求业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectPersonnelDemandSerImpl extends ServiceImpl<ProjectPersonnelDemand, ProjectPersonnelDemandDTO> implements ProjectPersonnelDemandSer {

    /**
     * 分页查询项目人员需求
     *
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    public List<ProjectPersonnelDemandBO> list(ProjectPersonnelDemandDTO dto) throws SerException {
        List<ProjectPersonnelDemand> list = super.findByPage(dto);
        List<ProjectPersonnelDemandBO> listBO = BeanTransform.copyProperties(list, ProjectPersonnelDemandBO.class);
        return listBO;
    }

    /**
     * 保存项目人员需求
     *
     * @param to 项目人员需求to
     * @return class ProjectPersonnelDemandBO
     * @throws SerException
     */
    @Override
    @Transactional
    public ProjectPersonnelDemandBO save(ProjectPersonnelDemandTO to) throws SerException {
        ProjectPersonnelDemand entity = BeanTransform.copyProperties(to, ProjectPersonnelDemand.class, true);
        entity = super.save(entity);
        ProjectPersonnelDemandBO bo = BeanTransform.copyProperties(entity, ProjectPersonnelDemandBO.class);
        return bo;
    }

    /**
     * 更新项目人员需求
     *
     * @param to 项目人员需求to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(ProjectPersonnelDemandTO to) throws SerException {
        ProjectPersonnelDemand entity = BeanTransform.copyProperties(to, ProjectPersonnelDemand.class, true);
        super.update(entity);
    }

    /**
     * 根据id删除项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}