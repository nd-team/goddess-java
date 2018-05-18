package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.vo.CommonalityVO;
import com.bjike.goddess.feedback.api.ProblemFeedbackAPI;
import com.bjike.goddess.feedback.bo.ProblemAcceptBO;
import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.excel.SonPermissionObject;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ProblemFeedbackTO;
import com.bjike.goddess.feedback.vo.ProblemAcceptVO;
import com.bjike.goddess.feedback.vo.ProblemFeedbackVO;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = problemFeedbackAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = problemFeedbackAPI.guidePermission(guidePermissionTO);
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

    /**
     * 根据名字获取岗位详细(地区，部门，模块，层级，岗位)
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/name")
    public Result name(String name, HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> userBOS = positionDetailUserAPI.getPositionDetail(name);
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询用户
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/sendObject")
    public Result sendObject(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结的公共邮箱
     *
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/email")
    public Result email(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.findThaw(), CommonalityVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 当前用户反馈的总条数
     *
     * @version v1
     */
    @GetMapping("v1/proCount")
    public Result currentUserProblemCount() throws ActException {
        try {
            return ActResult.initialize(problemFeedbackAPI.currentUserProblemCount());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}