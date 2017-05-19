package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.ProjectCostStatusAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectCostStatusBO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import com.bjike.goddess.projectmeasure.vo.ProjectCostStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目费用情况
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasure/projectcoststatus")
public class ProjectCostStatusAct {

    @Autowired
    private ProjectCostStatusAPI projectCostStatusAPI;

    /**
     * 分页查询项目费用情况
     *
     * @param dto 项目费用情况传输对象
     * @return class ProjectCostStatusVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectCostStatusDTO dto) throws ActException {
        try {
            List<ProjectCostStatusBO> boList = projectCostStatusAPI.list(dto);
            List<ProjectCostStatusVO> voList = BeanTransform.copyProperties(boList, ProjectCostStatusVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目费用情况
     *
     * @param to 项目费用情况to信息
     * @return class ProjectCostStatusVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectCostStatusTO to) throws ActException {
        try {
            ProjectCostStatusBO bo = projectCostStatusAPI.save(to);
            ProjectCostStatusVO vo = BeanTransform.copyProperties(bo, ProjectCostStatusVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectCostStatusAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目费用情况
     *
     * @param to 多项目单个界面to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectCostStatusTO to) throws ActException {
        try {
            projectCostStatusAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}