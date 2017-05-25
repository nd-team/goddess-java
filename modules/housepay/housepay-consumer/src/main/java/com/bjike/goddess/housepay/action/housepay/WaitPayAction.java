package com.bjike.goddess.housepay.action.housepay;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.api.WaitPayAPI;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.to.WaitPayTO;
import com.bjike.goddess.housepay.vo.WaitPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 等待付款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitpay")
public class WaitPayAction {
    @Autowired
    private WaitPayAPI waitPayAPI;

    /**
     * 等待付款列表总条数
     *
     * @param waitPayDTO 等待付款记录dto
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WaitPayDTO waitPayDTO) throws ActException {
        try {
            Long count = waitPayAPI.countWaitPay(waitPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个等待付款
     *
     * @param id
     * @return class WaitPayVO
     * @des 获取一个等待付款
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(waitPayBO, WaitPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 等待付款列表
     *
     * @param waitPayDTO 等待付款记录dto
     * @return class WaitPayVO
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WaitPayDTO waitPayDTO, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayVO> waitPayVOS = BeanTransform.copyProperties(
                    waitPayAPI.findListWaitPay(waitPayDTO), WaitPayVO.class, request);
            return ActResult.initialize(waitPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加等待付款
     *
     * @param waitPayTO 等待付款to
     * @return class WaitPayVO
     * @des 添加等待付款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.insertWaitPay(waitPayTO);
            return ActResult.initialize(waitPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayVO
     * @des 添加等待付款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.editWaitPay(waitPayTO);
            return ActResult.initialize(waitPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除等待付款
     *
     * @param id 用户id
     * @des 根据用户id删除等待付款
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.removeWaitPay(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 付款
     *
     * @param waitPayTO 付款数据to
     * @return class WaitPayVO
     * @des 付款
     * @version v1
     */
    @PostMapping("v1/payment")
    public Result payment(@Validated WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            PayRecordBO payRecordBO = waitPayAPI.payment(waitPayTO);
            return ActResult.initialize(payRecordBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}