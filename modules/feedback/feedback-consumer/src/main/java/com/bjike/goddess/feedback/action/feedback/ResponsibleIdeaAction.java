package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.ResponsibleIdeaAPI;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ResponsibleIdeaTO;
import com.bjike.goddess.feedback.vo.ResponsibleIdeaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 非责任相关人意见
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("responsibleidea")
public class ResponsibleIdeaAction {

    @Autowired
    private ResponsibleIdeaAPI responsibleIdeaAPI;

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

            Boolean isHasPermission = responsibleIdeaAPI.guidePermission(guidePermissionTO);
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
     * 非责任相关人意见列表总条数
     *
     * @param dto 非责任相关人意见dto
     * @des 获取所有非责任相关人意见总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ResponsibleIdeaDTO dto) throws ActException {
        try {
            Long count = responsibleIdeaAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个非责任相关人意见
     *
     * @param id id
     * @return class ResponsibleIdeaVO
     * @des 一个非责任相关人意见
     * @version v1
     */
    @GetMapping("v1/idea/{id}")
    public Result idea(@PathVariable String id) throws ActException {
        try {
            ResponsibleIdeaBO responsibleIdeaBO = responsibleIdeaAPI.getId(id);
            return ActResult.initialize(BeanTransform.copyProperties(responsibleIdeaBO, ResponsibleIdeaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 非责任相关人意见列表
     *
     * @param dto 非责任相关人意见dto
     * @return class ResponsibleIdeaVO
     * @des 获取所有非责任相关人意见
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ResponsibleIdeaDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ResponsibleIdeaVO> responsibleIdeaVOS = BeanTransform.copyProperties
                    (responsibleIdeaAPI.list(dto), ResponsibleIdeaVO.class, request);
            return ActResult.initialize(responsibleIdeaVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加非责任相关人意见
     *
     * @param to 非责任相关人意见数据to
     * @return class ResponsibleIdeaVO
     * @des 添加非责任相关人意见
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ResponsibleIdeaTO.TestAdd.class) ResponsibleIdeaTO to, BindingResult bindingResult) throws ActException {
        try {
            ResponsibleIdeaBO responsibleIdeaBO = responsibleIdeaAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(responsibleIdeaBO, ResponsibleIdeaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 采纳
     *
     * @param to 非责任相关人意见数据to
     * @return class ResponsibleIdeaVO
     * @des 采纳非责任相关人意见
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/adopt")
    public Result adopt(ResponsibleIdeaTO to, BindingResult bindingResult) throws ActException {
        try {
            ResponsibleIdeaBO responsibleIdeaBO = responsibleIdeaAPI.adopt(to);
            return ActResult.initialize(BeanTransform.copyProperties(responsibleIdeaBO, ResponsibleIdeaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}