package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.ProblemFeedbackAPI;
import com.bjike.goddess.feedback.bo.ProblemAcceptBO;
import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.to.ProblemFeedbackTO;
import com.bjike.goddess.feedback.vo.ProblemAcceptVO;
import com.bjike.goddess.feedback.vo.ProblemFeedbackVO;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 问题反馈模块
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("problemfeedback")
public class ProblemFeedbackAction {
    @Autowired
    private ProblemFeedbackAPI problemFeedbackAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 问题反馈模块列表总条数
     *
     * @param dto 问题反馈模块dto
     * @des 获取所有问题反馈模块总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblemFeedbackDTO dto) throws ActException {
        try {
            Long count = problemFeedbackAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个问题反馈模块
     *
     * @param id
     * @return class ProblemFeedbackVO
     * @des 获取一个问题反馈模块
     * @version v1
     */
    @GetMapping("v1/problem/{id}")
    public Result problem(@PathVariable String id) throws ActException {
        try {
            ProblemFeedbackBO bo = problemFeedbackAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProblemFeedbackVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题反馈模块列表
     *
     * @param dto 问题反馈模块dto
     * @return class ProblemFeedbackVO
     * @des 获取所有问题反馈模块
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProblemFeedbackDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProblemFeedbackVO> problemFeedbackVOS = BeanTransform.copyProperties
                    (problemFeedbackAPI.list(dto), ProblemFeedbackVO.class, request);
            return ActResult.initialize(problemFeedbackVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题反馈录入
     *
     * @param to 问题反馈模块数据to
     * @return class ProblemFeedbackVO
     * @des 添加问题反馈模块
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ProblemFeedbackTO.TestAdd.class) ProblemFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemFeedbackBO problemFeedbackBO = problemFeedbackAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemFeedbackBO, ProblemFeedbackVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题通报
     *
     * @param to 问题反馈模块数据to
     * @return class ProblemFeedbackVO
     * @des 编辑问题反馈模块
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(ProblemFeedbackTO.TestEdit.class) ProblemFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemFeedbackBO problemFeedbackBO = problemFeedbackAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemFeedbackBO, ProblemFeedbackVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题受理
     *
     * @param to 问题反馈模块数据to
     * @return class ProblemFeedbackVO
     * @des 编辑问题反馈模块
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/accept")
    public Result accept(@Validated(ProblemFeedbackTO.TestAccept.class) ProblemFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemAcceptBO problemAcceptBO = problemFeedbackAPI.problemAccept(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemAcceptBO, ProblemAcceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取关联相关模块
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

    /**
     * 获取问题责任人
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    @GetMapping("v1/name")
    public Result name(String name,HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> userBOS = positionDetailUserAPI.getPositionDetail(name);
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}