package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.ProjectMeasureSummaryAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.ProjectMeasureSummaryDTO;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;
import com.bjike.goddess.projectmeasure.vo.ProjectMeasureSummaryVO;
import com.bjike.goddess.projectmeasure.vo.ProjectMeasureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目测算邮件发送
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasure/projectmeasuresummary")
public class ProjectMeasureSummaryAction {

    @Autowired
    private ProjectMeasureSummaryAPI projectMeasureSummaryAPI;

    /**
     * 分页查询项目测算邮件发送
     *
     * @param dto 项目测算邮箱发送传输对象
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectMeasureSummaryDTO dto) throws ActException {
        try {
            List<ProjectMeasureSummaryBO> boList = projectMeasureSummaryAPI.list(dto);
            List<ProjectMeasureSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectMeasureSummaryVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目测算邮件发送
     *
     * @param to 项目测算邮件发送to信息
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectMeasureSummaryTO to) throws ActException {
        try {
            ProjectMeasureSummaryBO bo = projectMeasureSummaryAPI.save(to);
            ProjectMeasureSummaryVO vo = BeanTransform.copyProperties(bo, ProjectMeasureSummaryVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectMeasureSummaryAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目测算邮件发送
     *
     * @param to 项目测算邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectMeasureSummaryTO to) throws ActException {
        try {
            projectMeasureSummaryAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻项目测算邮件发送
     *
     * @param to 项目测算邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw")
    public Result thaw(@Validated({EDIT.class}) ProjectMeasureSummaryTO to) throws ActException {
        try {
            projectMeasureSummaryAPI.thaw(to);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目测算邮件发送
     *
     * @param to 项目测算邮件发送to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal")
    public Result congeal(@Validated({EDIT.class}) ProjectMeasureSummaryTO to) throws ActException {
        try {
            projectMeasureSummaryAPI.congeal(to);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目测算汇总
     *
     * @param to 项目测算邮件发送to信息
     * @return class ProjectMeasureVO
     * @throws ActException
     */
    @LoginAuth
    @GetMapping("v1/summarize")
    public Result summarize(ProjectMeasureSummaryTO to) throws ActException {
        try {
            List<ProjectMeasureBO> boList = projectMeasureSummaryAPI.summarize(to);
            List<ProjectMeasureVO> voList = BeanTransform.copyProperties(boList, ProjectMeasureVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}