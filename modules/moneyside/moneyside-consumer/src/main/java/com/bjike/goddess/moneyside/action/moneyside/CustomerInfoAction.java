package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.CustomerInfoAPI;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.bo.CustomerInfoBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.dto.CustomerInfoDTO;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import com.bjike.goddess.moneyside.to.CustomerInfoTO;
import com.bjike.goddess.moneyside.vo.CreditorsInvestVO;
import com.bjike.goddess.moneyside.vo.CustomerInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 客户信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:58 ]
 * @Description: [ 客户信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customerinfo")
public class CustomerInfoAction {
    @Autowired
    private CustomerInfoAPI customerInfoAPI;
    /**
     * 客户信息列表总条数
     *
     * @param customerInfoDTO 客户信息dto
     * @des 获取所有客户信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomerInfoDTO customerInfoDTO) throws ActException {
        try {
            Long count = customerInfoAPI.countCustomerInfo(customerInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户信息
     *
     * @param id
     * @return class CustomerInfoVO
     * @des 获取一个客户信息
     * @version v1
     */
    @GetMapping("v1/customer/{id}")
    public Result customer(@PathVariable String id) throws ActException {
        try {
            CustomerInfoBO customerInfoBO = customerInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(customerInfoBO, CustomerInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户信息列表
     *
     * @param customerInfoDTO 客户信息dto
     * @return class CustomerInfoVO
     * @des 获取所有客户信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CustomerInfoDTO customerInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<CustomerInfoVO> customerInfoVOS = BeanTransform.copyProperties
                    (customerInfoAPI.findListCustomerInfo(customerInfoDTO), CustomerInfoVO.class, request);
            return ActResult.initialize(customerInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户信息
     *
     * @param customerInfoTO 客户信息数据to
     * @return class CustomerInfoVO
     * @des 添加客户信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(CustomerInfoTO.TestAdd.class) CustomerInfoTO customerInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CustomerInfoBO customerInfoBO = customerInfoAPI.insertCustomerInfo(customerInfoTO);
            return ActResult.initialize(customerInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑客户信息
     *
     * @param customerInfoTO 客户信息数据to
     * @return class CustomerInfoVO
     * @des 编辑客户信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(CustomerInfoTO.TestEdit.class) CustomerInfoTO customerInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CustomerInfoBO customerInfoBO = customerInfoAPI.editCustomerInfo(customerInfoTO);
            return ActResult.initialize(customerInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除客户信息
     *
     * @param id 用户id
     * @des 根据用户id删除客户信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            customerInfoAPI.removeCustomerInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}