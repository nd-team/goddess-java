package com.bjike.goddess.budget.action.budget;

import com.bjike.goddess.budget.api.ProjectWeekAPI;
import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.bo.ProjectWeekCountBO;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.to.ProjectWeekTO;
import com.bjike.goddess.budget.vo.ProjectWeekCountVO;
import com.bjike.goddess.budget.vo.ProjectWeekVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目收入周
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectweek")
public class ProjectWeekAct {
    @Autowired
    private ProjectWeekAPI projectWeekAPI;

    /**
     * 添加
     *
     * @param to      项目收入周信息
     * @param request 请求对象
     * @return class ProjectWeekVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) ProjectWeekTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectWeekBO bo = projectWeekAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProjectWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 项目收入周信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectWeekTO to, BindingResult result) throws ActException {
        try {
            projectWeekAPI.edit(to);
            return new ActResult("edit success！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 项目收入周id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectWeekAPI.delete(id);
            return new ActResult("删除成功！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     项目收入周分页信息
     * @param request 请求对象
     * @return class ProjectWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectWeekDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectWeekBO> list = projectWeekAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      项目收入周id
     * @param request 请求对象
     * @return class ProjectWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectweek/{id}")
    public Result projectweek(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectWeekBO bo = projectWeekAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProjectWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param request 请求对象
     * @return class ProjectWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HttpServletRequest request) throws ActException {
        try {
            List<ProjectWeekCountBO> list = projectWeekAPI.count();
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 按项目汇总
     *
     * @param projects 项目数组
     * @param request  请求对象
     * @return class ProjectWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/conditionsCount/{projects}")
    public Result conditionsCount(@PathVariable String[] projects, HttpServletRequest request) throws ActException {
        try {
            List<ProjectWeekCountBO> list = projectWeekAPI.conditionsCount(projects);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(ProjectWeekDTO dto) throws ActException {
        try {
            Long num = projectWeekAPI.countNum(dto);
            return ActResult.initialize(num);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有项目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllProjects")
    public Result findAllProjects() throws ActException {
        try {
            List<String> list = projectWeekAPI.findAllProjects();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}