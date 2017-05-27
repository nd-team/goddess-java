package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.ProjectPersonnelDemandAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectPersonnelDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectPersonnelDemandDTO;
import com.bjike.goddess.projectmeasure.to.ProjectPersonnelDemandTO;
import com.bjike.goddess.projectmeasure.vo.ProjectPersonnelDemandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 项目人员需求
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectpersonneldemand")
public class ProjectPersonnelDemandAct {

    @Autowired
    private ProjectPersonnelDemandAPI projectPersonnelDemandAPI;

    /**
     * 根据id查询项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @return class ProjectPersonnelDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/projectbasicinfo/{id}")
    public Result findById(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            ProjectPersonnelDemandBO bo = projectPersonnelDemandAPI.findById(id);
            ProjectPersonnelDemandVO vo = BeanTransform.copyProperties(bo, ProjectPersonnelDemandVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 项目人员需求dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated ProjectPersonnelDemandDTO dto, BindingResult result) throws ActException {
        try {
            Long count = projectPersonnelDemandAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目人员需求
     *
     * @param dto 项目人员需求传输对象
     * @return class ProjectPersonnelDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(ProjectPersonnelDemandDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectPersonnelDemandBO> boList = projectPersonnelDemandAPI.list(dto);
            List<ProjectPersonnelDemandVO> voList = BeanTransform.copyProperties(boList, ProjectPersonnelDemandVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目人员需求
     *
     * @param to 项目人员需求to信息
     * @return class ProjectPersonnelDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ProjectPersonnelDemandTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectPersonnelDemandBO bo = projectPersonnelDemandAPI.save(to);
            ProjectPersonnelDemandVO vo = BeanTransform.copyProperties(bo, ProjectPersonnelDemandVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目人员需求
     *
     * @param id 项目人员需求唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectPersonnelDemandAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目人员需求
     *
     * @param to 项目人员需求to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ProjectPersonnelDemandTO to, BindingResult result) throws ActException {
        try {
            projectPersonnelDemandAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}