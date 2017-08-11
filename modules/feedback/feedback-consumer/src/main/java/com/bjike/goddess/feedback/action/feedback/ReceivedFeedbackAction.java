package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.ReceivedFeedbackAPI;
import com.bjike.goddess.feedback.bo.ProblemFeedbackBO;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.bo.ReceivedFeedbackBO;
import com.bjike.goddess.feedback.dto.ProblemFeedbackDTO;
import com.bjike.goddess.feedback.dto.ReceivedFeedbackDTO;
import com.bjike.goddess.feedback.entity.ReceivedFeedback;
import com.bjike.goddess.feedback.to.ProblemFeedbackTO;
import com.bjike.goddess.feedback.to.ReceivedFeedbackTO;
import com.bjike.goddess.feedback.vo.ProblemFeedbackVO;
import com.bjike.goddess.feedback.vo.ProblemResultVO;
import com.bjike.goddess.feedback.vo.ReceivedFeedbackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 已受理的反馈
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 09:24 ]
 * @Description: [ 已受理的反馈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("receivedfeedback")
public class ReceivedFeedbackAction{
    @Autowired
    private ReceivedFeedbackAPI receivedFeedbackAPI;
    /**
     * 已受理的反馈列表总条数
     *
     * @param dto 已受理的反馈dto
     * @des 获取所有已受理的反馈总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ReceivedFeedbackDTO dto) throws ActException {
        try {
            Long count = receivedFeedbackAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个已受理的反馈
     *
     * @param id
     * @return class ReceivedFeedbackVO
     * @des 获取一个已受理的反馈
     * @version v1
     */
    @GetMapping("v1/received/{id}")
    public Result received(@PathVariable String id) throws ActException {
        try {
            ReceivedFeedbackBO bo = receivedFeedbackAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ReceivedFeedbackVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已受理的反馈列表
     *
     * @param dto 已受理的反馈dto
     * @return class ReceivedFeedbackVO
     * @des 获取所有已受理的反馈
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ReceivedFeedbackDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ReceivedFeedbackVO> receivedFeedbackVOS = BeanTransform.copyProperties
                    (receivedFeedbackAPI.list(dto), ReceivedFeedbackVO.class, request);
            return ActResult.initialize(receivedFeedbackVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 提供建议
     *
     * @param to 已受理的反馈数据to
     * @return class ReceivedFeedbackVO
     * @des 提供建议
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/advice")
    public Result advice(@Validated(ReceivedFeedbackTO.TestAdvice.class) ReceivedFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            ReceivedFeedbackBO receivedFeedbackBO = receivedFeedbackAPI.provideAdvice(to);
            return ActResult.initialize(BeanTransform.copyProperties(receivedFeedbackBO, ReceivedFeedbackVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑优先级
     *
     * @param to 已受理的反馈数据to
     * @return class ReceivedFeedbackVO
     * @des 编辑优先级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/priority")
    public Result priority(@Validated(ReceivedFeedbackTO.TestPriority.class) ReceivedFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            ReceivedFeedbackBO receivedFeedbackBO = receivedFeedbackAPI.priority(to);
            return ActResult.initialize(BeanTransform.copyProperties(receivedFeedbackBO, ReceivedFeedbackVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 最终解决方案录入
     *
     * @param to 已受理的反馈数据to
     * @return class ProblemResultVO
     * @des 最终解决方案录入
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/solve")
    public Result solve(@Validated(ReceivedFeedbackTO.TestSolve.class) ReceivedFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            ProblemResultBO problemFeedbackBO = receivedFeedbackAPI.solve(to);
            return ActResult.initialize(BeanTransform.copyProperties(problemFeedbackBO, ProblemResultVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}