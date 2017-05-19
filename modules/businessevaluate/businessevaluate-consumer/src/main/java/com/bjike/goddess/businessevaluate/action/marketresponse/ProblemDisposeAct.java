package com.bjike.goddess.businessevaluate.action.marketresponse;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.api.ProblemDisposeAPI;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.to.ProblemDisposeTO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.businessevaluate.vo.ProblemDisposeVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.DepartmentAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.DepartmentBO;
import com.bjike.goddess.user.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目问题受理和处理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("problemdispose")
public class ProblemDisposeAct {

    @Autowired
    private ProblemDisposeAPI problemDisposeAPI;
    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;
    @Autowired
    private DepartmentAPI departmentAPI;

    /**
     * 查询所有部门
     *
     * @version v1
     */
    @GetMapping("v1/departments")
    public Result departments(HttpServletRequest request) throws ActException {
//        List<DepartmentVO> voList = BeanTransform.copyProperties(departmentAPI.treeData(),DepartmentVO.class);
        return ActResult.initialize("voList");
    }

    /**
     * 根据部门查询模块
     *
     * @version v1
     */
    @GetMapping("v1/module")
    public Result module(String departmentId,HttpServletRequest request) throws ActException {
        return ActResult.initialize("success");
    }

    /**
     * 查询所有项目
     *
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/porjects")
    public Result porjects(HttpServletRequest request) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAll(), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemDisposeDTO dto) throws ActException {
        try {
            Long count = problemDisposeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目问题受理和处理
     *
     * @param id 项目问题受理和处理id
     * @return class ProblemDisposeVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.findById(id), ProblemDisposeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @return class ProblemDisposeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProblemDisposeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.addModel(to), ProblemDisposeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目问题受理和处理
     *
     * @param to 项目问题受理和处理
     * @return class ProblemDisposeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProblemDisposeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.editModel(to), ProblemDisposeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑定性指标
     *
     * @param to 定性指标信息
     * @return class ProblemDisposeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/qualitative")
    public Result qualitativeKPI(@Validated({ProblemDisposeTO.Qualitative.class}) ProblemDisposeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.editModel(to), ProblemDisposeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑定量指标
     *
     * @param to 定量指标
     * @return class ProblemDisposeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/ration")
    public Result rationKPI(@Validated({ProblemDisposeTO.Ration.class}) ProblemDisposeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblemDisposeVO vo = BeanTransform.copyProperties(problemDisposeAPI.editModel(to), ProblemDisposeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目问题受理和处理
     *
     * @param id 项目问题受理和处理ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            problemDisposeAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class ProblemDisposeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ProblemDisposeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProblemDisposeVO> voList = BeanTransform.copyProperties(problemDisposeAPI.pageList(dto), ProblemDisposeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}