package com.bjike.goddess.negotiatemeeting.action.negotiatemeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.negotiatemeeting.api.SummaryFeedbackAPI;
import com.bjike.goddess.negotiatemeeting.bo.SummaryFeedbackBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryFeedbackDTO;
import com.bjike.goddess.negotiatemeeting.to.SummaryFeedbackTO;
import com.bjike.goddess.negotiatemeeting.vo.SummaryFeedbackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 纪要反馈投诉
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:54 ]
 * @Description: [ 纪要反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("summaryfeedback")
public class SummaryFeedbackAct {
    @Autowired
    private SummaryFeedbackAPI summaryFeedbackAPI;

    /**
     * 纪要反馈投诉列表总条数
     *
     * @param dto 纪要反馈投诉dto
     * @des 获取所有纪要反馈投诉总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(SummaryFeedbackDTO dto) throws ActException {
        try {
            Long count = summaryFeedbackAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个纪要反馈投诉
     *
     * @param id id
     * @return class SummaryFeedbackVO
     * @des 获取一个纪要反馈投诉
     * @version v1
     */
    @GetMapping("v1/summaryfeedback/{id}")
    public Result summaryfeedback(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SummaryFeedbackBO bo = summaryFeedbackAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SummaryFeedbackVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 纪要反馈投诉列表
     *
     * @param dto 纪要反馈投诉信息dto
     * @return class SummaryFeedbackVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SummaryFeedbackDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SummaryFeedbackVO> VOS = BeanTransform.copyProperties
                    (summaryFeedbackAPI.list(dto), SummaryFeedbackVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加纪要反馈投诉信息
     *
     * @param to 纪要反馈投诉信息数据to
     * @return class SummaryFeedbackVO
     * @version v1
     */
    //  @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) SummaryFeedbackTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            SummaryFeedbackBO bo = summaryFeedbackAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SummaryFeedbackVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑纪要反馈投诉信息
     *
     * @param to 纪要反馈投诉信息数据to
     * @return class SummaryFeedbackVO
     * @des 编辑纪要反馈投诉信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SummaryFeedbackTO to, BindingResult bindingResult) throws ActException {
        try {
            summaryFeedbackAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}