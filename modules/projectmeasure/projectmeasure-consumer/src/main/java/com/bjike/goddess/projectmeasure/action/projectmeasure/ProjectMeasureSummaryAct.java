package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("projectmeasuresummary")
public class ProjectMeasureSummaryAct {

    @Autowired
    private ProjectMeasureSummaryAPI projectMeasureSummaryAPI;

    /**
     * 根据id查询项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/projectbasicinfo/{id}")
    public Result findById(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            ProjectMeasureSummaryBO bo = projectMeasureSummaryAPI.findById(id);
            ProjectMeasureSummaryVO vo = BeanTransform.copyProperties(bo, ProjectMeasureSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 项目测算邮件发送dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated ProjectMeasureSummaryDTO dto, BindingResult result) throws ActException {
        try {
            Long count = projectMeasureSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目测算邮件发送
     *
     * @param dto 项目测算邮箱发送传输对象
     * @return class ProjectMeasureSummaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectMeasureSummaryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMeasureSummaryBO> boList = projectMeasureSummaryAPI.list(dto);
            List<ProjectMeasureSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectMeasureSummaryVO.class, request);
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
    public Result add(@Validated(value = {ADD.class}) ProjectMeasureSummaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectMeasureSummaryBO bo = projectMeasureSummaryAPI.save(to);
            ProjectMeasureSummaryVO vo = BeanTransform.copyProperties(bo, ProjectMeasureSummaryVO.class, request);
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
    public Result edit(@Validated(value = {EDIT.class}) ProjectMeasureSummaryTO to, BindingResult result) throws ActException {
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
     * @param id 项目测算邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            projectMeasureSummaryAPI.thaw(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结项目测算邮件发送
     *
     * @param id 项目测算邮件发送唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            projectMeasureSummaryAPI.congeal(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目测算汇总
     *
     * @param areas 汇总地区
     * @return class ProjectMeasureVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize")
    public Result summarize(String[] areas, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMeasureBO> boList = projectMeasureSummaryAPI.summarize(areas);
            List<ProjectMeasureVO> voList = BeanTransform.copyProperties(boList, ProjectMeasureVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}