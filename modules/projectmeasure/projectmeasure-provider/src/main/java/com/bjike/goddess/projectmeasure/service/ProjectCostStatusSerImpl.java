package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectCostStatusBO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectCostStatus;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional(rollbackFor = {SerException.class})
    public ProjectCostStatusBO save(ProjectCostStatusTO to) throws SerException {
        ProjectCostStatus entity = BeanTransform.copyProperties(to, ProjectCostStatus.class, true);
        verify(entity);
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
    @Transactional(rollbackFor = {SerException.class})
    public void update(ProjectCostStatusTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ProjectCostStatus model = super.findById(to.getId());
            if (model != null) {
                updateProjectCostStatus(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新项目费用情况
     *
     * @param to
     * @param model
     */
    private void updateProjectCostStatus(ProjectCostStatusTO to, ProjectCostStatus model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        verify(model);//参数校验
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     *
     * @param model
     */
    private void verify(ProjectCostStatus model) throws SerException {
        if ((model.getTaxes() != null) && (model.getTaxes() < 0)) {
            throw new SerException("参数税金taxes必须是大于等于0的小数");
        }

        if ((model.getServiceCharge() != null) && (model.getServiceCharge() < 0)) {
            throw new SerException("参数服务费serviceCharge必须是大于等于0的小数");
        }

        if ((model.getServeCharge() != null) && (model.getServeCharge() < 0)) {
            throw new SerException("参数招待费serveCharge必须是大于等于0的小数");
        }

        if ((model.getRoyalties() != null) && (model.getRoyalties() < 0)) {
            throw new SerException("参数提成royalties必须是大于等于0的小数");
        }

        if ((model.getDemandCharge() != null) && (model.getDemandCharge() < 0)) {
            throw new SerException("参数需求费用demandCharge必须是大于等于0的小数");
        }
    }

    /**
     * 根据id删除项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}