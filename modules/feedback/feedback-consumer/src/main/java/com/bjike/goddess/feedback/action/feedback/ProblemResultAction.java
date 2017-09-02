package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.ProblemResultAPI;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.dto.ProblemResultDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemResultTO;
import com.bjike.goddess.feedback.vo.ProblemResultVO;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 问题处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("problemresult")
public class ProblemResultAction {
    @Autowired
    private ProblemResultAPI problemResultAPI;
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

            Boolean isHasPermission = problemResultAPI.guidePermission(guidePermissionTO);
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
     * 问题处理结果列表总条数
     *
     * @param dto 问题处理结果dto
     * @des 获取所有问题处理结果总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemResultDTO dto) throws ActException {
        try {
            Long count = problemResultAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个问题处理结果
     *
     * @param id
     * @return class ProblemFeedbackVO
     * @des 获取一个问题处理结果
     * @version v1
     */
    @GetMapping("v1/problem/{id}")
    public Result problem(@PathVariable String id) throws ActException {
        try {
            ProblemResultBO bo = problemResultAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProblemResultVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题处理结果列表
     *
     * @param dto 问题处理结果dto
     * @return class ProblemResultVO
     * @des 获取所有问题处理结果
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProblemResultDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProblemResultVO> problemResultVOS = BeanTransform.copyProperties
                    (problemResultAPI.list(dto), ProblemResultVO.class, request);
            return ActResult.initialize(problemResultVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 当事人确认
     *
     * @param to 问题处理结果数据to
     * @return class ProblemResultVO
     * @des 当事人确认
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/party")
    public Result party(@Validated(ProblemResultTO.TestParty.class) ProblemResultTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemResultBO problemResultBO = problemResultAPI.partyConfirm(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemResultBO, ProblemResultVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认是否需要协调
     *
     * @param to 问题处理结果数据to
     * @return class ProblemResultVO
     * @des 确认是否需要协调
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/coordinate")
    public Result coordinate(@Validated(ProblemResultTO.TestCoordinate.class) ProblemResultTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemResultBO problemResultBO = problemResultAPI.coordinate(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemResultBO, ProblemResultVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 协调结果
     *
     * @param to 问题处理结果数据to
     * @return class ProblemResultVO
     * @des 协调结果
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/result")
    public Result result(@Validated(ProblemResultTO.TestResult.class) ProblemResultTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemResultBO problemResultBO = problemResultAPI.result(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemResultBO, ProblemResultVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有模块
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/module")
    public Result module(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(Status.THAW), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}