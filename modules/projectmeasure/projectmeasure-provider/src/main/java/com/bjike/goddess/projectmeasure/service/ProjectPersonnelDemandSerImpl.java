package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectPersonnelDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectPersonnelDemand;
import com.bjike.goddess.projectmeasure.to.ProjectPersonnelDemandTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional(rollbackFor = {SerException.class})
    public ProjectPersonnelDemandBO save(ProjectPersonnelDemandTO to) throws SerException {
        ProjectPersonnelDemand entity = BeanTransform.copyProperties(to, ProjectPersonnelDemand.class, true);
        verify(entity);//参数校验
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
    @Transactional(rollbackFor = {SerException.class})
    public void update(ProjectPersonnelDemandTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ProjectPersonnelDemand model = super.findById(to.getId());
            if (model != null) {
                updateProjectPersonnelDemand(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新项目人员需求
     * @param to
     * @param model
     */
    private void updateProjectPersonnelDemand(ProjectPersonnelDemandTO to, ProjectPersonnelDemand model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        verify(model);//参数校验
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 校验参数
     * @param model
     */
    private void verify(ProjectPersonnelDemand model) throws SerException {
        if ((model.getYearsOfWork() != null) && (model.getYearsOfWork() < 0)) {
            throw new SerException("参数工作年限yearsOfWork必须是大于等于0的小数");
        }

        if ((model.getNormalLaborCost() != null) && (model.getNormalLaborCost() <= 0)) {
            throw new SerException("参数项目期间正常人工成本normalLaborCost必须是大于等于0的整数");
        }

        if ((model.getOvertimeLaborCost() != null) && (model.getOvertimeLaborCost() <=0)) {
            throw new SerException("参数项目期间加班人工成本overtimeLaborCost必须是大于等于0的整数");
        }
    }

    /**
     * 根据id删除项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}