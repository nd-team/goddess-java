package com.bjike.goddess.staffpay.action.staffpay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.api.FirstPayRecordAPI;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.bo.WaitPayBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.dto.WaitPayDTO;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import com.bjike.goddess.staffpay.to.WaitPayTO;
import com.bjike.goddess.staffpay.vo.FirstPayRecordVO;
import com.bjike.goddess.staffpay.vo.WaitPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 第一次已付款记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("firstpayrecord")
public class FirstPayRecordAction {
    @Autowired
    private FirstPayRecordAPI firstPayRecordAPI;

    /**
     * 第一次已付款记录列表总条数
     *
     * @param firstPayRecordDTO 第一次已付款记录dto
     * @des 获取所有第一次已付款记录
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FirstPayRecordDTO firstPayRecordDTO) throws ActException {
        try {
            Long count = firstPayRecordAPI.countFirstPayRecord(firstPayRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个第一次已付款记录
     *
     * @param id
     * @return class FirstPayRecordVO
     * @des 获取一个第一次已付款记录
     * @version v1
     */
    @GetMapping("v1/first/{id}")
    public Result first(@PathVariable String id) throws ActException {
        try {
            FirstPayRecordBO firstPayRecordBO = firstPayRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(firstPayRecordBO, FirstPayRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 第一次已付款记录
     *
     * @param firstPayRecordDTO 第一次已付款记录dto
     * @return class FirstPayRecordVO
     * @des 获取所有第一次已付款记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FirstPayRecordDTO firstPayRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<FirstPayRecordVO> firstPayRecordVOS = BeanTransform.copyProperties(
                    firstPayRecordAPI.findListFirstPayRecord(firstPayRecordDTO),FirstPayRecordVO.class, request);
            return ActResult.initialize(firstPayRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除第一次已付款记录
     *
     * @param id 用户id
     * @des 根据用户id删除第一次已付款记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firstPayRecordAPI.removeFirstPayRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 付款
     *
     * @param firstPayRecordTO 付款数据to
     * @return class FirstPayRecordVO
     * @des 付款
     * @version v1
     */
    @PostMapping("v1/payment")
    public Result payment(@Validated FirstPayRecordTO firstPayRecordTO, BindingResult bindingResult) throws ActException {
        try {
            PayRecordBO payRecordBO = firstPayRecordAPI.payment(firstPayRecordTO);
            return ActResult.initialize(payRecordBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}