package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.OtherIdeaAPI;
import com.bjike.goddess.feedback.bo.OtherIdeaBO;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.OtherIdeaDTO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.entity.OtherIdea;
import com.bjike.goddess.feedback.to.OtherIdeaTO;
import com.bjike.goddess.feedback.to.ResponsibleIdeaTO;
import com.bjike.goddess.feedback.vo.OtherIdeaVO;
import com.bjike.goddess.feedback.vo.ResponsibleIdeaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 其他模块意见
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("otheridea")
public class OtherIdeaAction {
    @Autowired
    private OtherIdeaAPI otherIdeaAPI;
    /**
     * 其他模块意见列表总条数
     *
     * @param dto 其他模块意见dto
     * @des 获取所有其他模块意见总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OtherIdeaDTO dto) throws ActException {
        try {
            Long count = otherIdeaAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 其他模块意见列表
     *
     * @param dto 其他模块意见dto
     * @return class ResponsibleIdeaVO
     * @des 获取所有其他模块意见
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OtherIdeaDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<OtherIdeaVO> otherIdeaVOS = BeanTransform.copyProperties
                    (otherIdeaAPI.list(dto), OtherIdeaVO.class, request);
            return ActResult.initialize(otherIdeaVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加其他模块意见
     *
     * @param to 其他模块意见数据to
     * @return class ResponsibleIdeaVO
     * @des 添加其他模块意见
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(OtherIdeaTO.TestAdd.class) OtherIdeaTO to, BindingResult bindingResult) throws ActException {
        try {
            OtherIdeaBO otherIdeaBO = otherIdeaAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(otherIdeaBO,OtherIdeaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}