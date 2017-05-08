package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.ProjectMeasureSummaryDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectMeasureSummary;
import com.bjike.goddess.projectmeasure.service.ProjectMeasureSummarySer;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目测算汇总业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectMeasureSummaryApiImpl")
public class ProjectMeasureSummaryApiImpl implements ProjectMeasureSummaryAPI {

    @Autowired
    private ProjectMeasureSummarySer projectMeasureSummarySer;

    /**
     * 根据id查询项目测算汇总
     *
     * @param id 项目测算汇总唯一标识
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    public ProjectMeasureSummaryBO findById(String id) throws SerException {
        ProjectMeasureSummary model = projectMeasureSummarySer.findById(id);
        return BeanTransform.copyProperties(model, ProjectMeasureSummaryBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 项目测算汇总dto
     * @throws SerException
     */
    @Override
    public Long count(ProjectMeasureSummaryDTO dto) throws SerException {
        return projectMeasureSummarySer.count(dto);
    }

    /**
     * 分页查询项目测算汇总邮件发送
     *
     * @param dto 项目测算汇总dto
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureSummaryBO> list(ProjectMeasureSummaryDTO dto) throws SerException {
        return projectMeasureSummarySer.list(dto);
    }

    /**
     * 保存项目测算汇总邮件列表
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    public ProjectMeasureSummaryBO save(ProjectMeasureSummaryTO to) throws SerException {
        return projectMeasureSummarySer.save(to);
    }

    /**
     * 根据id删除项目测算汇总邮件记录
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        projectMeasureSummarySer.remove(id);
    }

    /**
     * 更新项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @throws SerException
     */
    @Override
    public void update(ProjectMeasureSummaryTO to) throws SerException {
        projectMeasureSummarySer.update(to);
    }

    /**
     * 解冻项目测算汇总邮件记录
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    public void thaw(String id) throws SerException {
        projectMeasureSummarySer.thaw(id);
    }

    /**
     * 冻结项目测算汇总邮件记录
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    public void congeal(String id) throws SerException {
        projectMeasureSummarySer.congeal(id);
    }

    /**
     * 项目测算管理汇总
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureBO> summarize(ProjectMeasureSummaryTO to) throws SerException {
        return projectMeasureSummarySer.summarize(to);
    }
}