package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.ProjectMeasureSummaryDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectMeasureSummary;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目测算汇总业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectMeasureSummarySerImpl extends ServiceImpl<ProjectMeasureSummary, ProjectMeasureSummaryDTO> implements ProjectMeasureSummarySer {

    /**
     * 分页查询项目测算汇总邮件发送
     *
     * @param dto 项目测算汇总dto
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureSummaryBO> list(ProjectMeasureSummaryDTO dto) throws SerException {
        List<ProjectMeasureSummary> list = super.findByPage(dto);
        List<ProjectMeasureSummaryBO> listBO = BeanTransform.copyProperties(list, ProjectMeasureSummaryBO.class);
        return listBO;
    }

    /**
     * 添加项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    @Transactional
    public ProjectMeasureSummaryBO save(ProjectMeasureSummaryTO to) throws SerException {
        ProjectMeasureSummary entity = BeanTransform.copyProperties(to, ProjectMeasureSummary.class, true);
        entity = super.save(entity);
        ProjectMeasureSummaryBO bo = BeanTransform.copyProperties(entity, ProjectMeasureSummaryBO.class);
        return bo;
    }

    /**
     * 更新项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(ProjectMeasureSummaryTO to) throws SerException {
        ProjectMeasureSummary entity = BeanTransform.copyProperties(to, ProjectMeasureSummary.class, true);
        super.update(entity);
    }

    /**
     * 冻结项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @throws SerException
     */
    @Override
    @Transactional
    public void thaw(ProjectMeasureSummaryTO to) throws SerException {
        to.setStatus(Status.THAW);//将状态修改为冻结状态
        update(to);//执行更新操作
    }

    /**
     * 解冻项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @throws SerException
     */
    @Override
    @Transactional
    public void congeal(ProjectMeasureSummaryTO to) throws SerException {
        to.setStatus(Status.CONGEAL);//将状态修改为解冻状态
        update(to);//执行更新操作
    }

    /**
     * 项目预算管理汇总
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureBO> summarize(ProjectMeasureSummaryTO to) throws SerException {
        // TODO: 17-3-23
        //汇总功能
        return null;
    }
}