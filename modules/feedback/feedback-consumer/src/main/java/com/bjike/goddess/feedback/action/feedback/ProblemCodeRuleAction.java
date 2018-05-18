package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.ProblemCodeRuleAPI;
import com.bjike.goddess.feedback.bo.ProblemCodeRuleBO;
import com.bjike.goddess.feedback.dto.ProblemCodeRuleDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemCodeRuleTO;
import com.bjike.goddess.feedback.vo.ProblemCodeRuleVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 问题编码规则
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("problemcoderule")
public class ProblemCodeRuleAction {
    @Autowired
    private ProblemCodeRuleAPI problemCodeRuleAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = problemCodeRuleAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题编码规则列表总条数
     *
     * @param dto 问题编码规则dto
     * @des 获取所有问题编码规则总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemCodeRuleDTO dto) throws ActException {
        try {
            Long count = problemCodeRuleAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个问题编码规则
     *
     * @param id
     * @return class ProblemCodeRuleVO
     * @des 获取一个问题编码规则
     * @version v1
     */
    @GetMapping("v1/code/{id}")
    public Result code(@PathVariable String id) throws ActException {
        try {
            ProblemCodeRuleBO bo = problemCodeRuleAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProblemCodeRuleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题编码规则列表
     *
     * @param dto 问题编码规则dto
     * @return class ProblemCodeRuleVO
     * @des 获取所有问题编码规则
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProblemCodeRuleDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProblemCodeRuleVO> problemCodeRuleVOS = BeanTransform.copyProperties
                    (problemCodeRuleAPI.list(dto), ProblemCodeRuleVO.class, request);
            return ActResult.initialize(problemCodeRuleVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑问题编码规则
     *
     * @param to 问题编码规则数据to
     * @return class ProblemCodeRuleVO
     * @des 编辑问题编码规则
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(ProblemCodeRuleTO.TestEdit.class) ProblemCodeRuleTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemCodeRuleBO problemCodeRuleBO = problemCodeRuleAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemCodeRuleBO, ProblemCodeRuleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findThawOpinion")
    public Result findThawOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findThawOpinion(), OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的模块类型数据
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(Status.THAW), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}