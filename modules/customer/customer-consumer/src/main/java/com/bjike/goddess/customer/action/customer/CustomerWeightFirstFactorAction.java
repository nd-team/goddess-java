package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CustomerWeightFirstFactorAPI;
import com.bjike.goddess.customer.bo.CustomerWeightFirstFactorBO;
import com.bjike.goddess.customer.dto.CustomerWeightFirstFactorDTO;
import com.bjike.goddess.customer.to.CustomerWeightFirstFactorTO;
import com.bjike.goddess.customer.vo.CustomerWeightFirstFactorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户权重一层因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:16 ]
 * @Description: [ 客户权重一层因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customerweightfirstfactor")
public class CustomerWeightFirstFactorAction {

    @Autowired
    private CustomerWeightFirstFactorAPI customerWeightFirstFactorAPI;

    /**
     * 客户权重一层因素层设置列表总条数
     *
     * @param customerWeightFirstFactorDTO 客户权重一层因素层设置dto
     * @des 获取所有客户权重一层因素层设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws ActException {
        try {
            Long count = customerWeightFirstFactorAPI.countFirstFactor(customerWeightFirstFactorDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 客户权重一层因素层设置列表
     *
     * @param customerWeightFirstFactorDTO 客户权重一层因素层设置dto
     * @return class CustomerWeightFirstFactorVO
     * @des 获取所有客户权重一层因素层设置
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws ActException {
        try {

            List<CustomerWeightFirstFactorBO> customerWeightFirstFactorBOS = customerWeightFirstFactorAPI.listFirstFactor(customerWeightFirstFactorDTO);
            List<CustomerWeightFirstFactorVO> customerWeightFirstFactorVOS = BeanTransform.copyProperties(customerWeightFirstFactorBOS, CustomerWeightFirstFactorVO.class);
            return ActResult.initialize(customerWeightFirstFactorVOS);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加客户权重一层因素层设置
     *
     * @param customerWeightFirstFactorTO 客户权重一层因素层设置to
     * @return class CustomerWeightFirstFactorVO
     * @des 添加客户权重一层因素层设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBussWeight(@Validated(ADD.class) CustomerWeightFirstFactorTO customerWeightFirstFactorTO, BindingResult bindingResult) throws ActException {
        try {

            CustomerWeightFirstFactorBO customerWeightFirstFactorBO = customerWeightFirstFactorAPI.addFirstFactor(customerWeightFirstFactorTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerWeightFirstFactorBO, CustomerWeightFirstFactorVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户权重一层因素层设置
     *
     * @param customerWeightFirstFactorTO 客户权重一层因素层设置数据bo
     * @return class CustomerWeightFirstFactorVO
     * @des 添加客户权重一层因素层设置
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editBussWeight(@Validated(EDIT.class) CustomerWeightFirstFactorTO customerWeightFirstFactorTO, BindingResult bindingResult) throws ActException {
        try {

            CustomerWeightFirstFactorBO customerWeightFirstFactorBO = customerWeightFirstFactorAPI.editFirstFactor(customerWeightFirstFactorTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerWeightFirstFactorBO, CustomerWeightFirstFactorVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户权重一层因素层设置
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBussWeight(@PathVariable String id) throws ActException {
        try {

            customerWeightFirstFactorAPI.deleteFirstFactor(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}