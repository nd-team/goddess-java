package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.vo.CustomerBaseInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客户基本信息
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.059 ]
 * @Description: [ 客户基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customer/customerbaseinfo")
public class CustomerBaseInfoAction {


    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;

    /**
     * 客户基本列表
     *
     * @param customerBaseInfoDTO 客户基本信息dto
     * @return class CustomerBaseInfoVO
     * @des 获取所有客户基本信息
     * @version v1
     */
    @GetMapping("v1/listCustomerBaseInfo")
    public Result findListCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws ActException {
        try {
            List<CustomerBaseInfoVO> customerBaseInfoVOList = BeanTransform.copyProperties(
                    customerBaseInfoAPI.listCustomerBaseInfo(customerBaseInfoDTO), CustomerBaseInfoVO.class, true);
            return ActResult.initialize(customerBaseInfoVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自动编号
     *
     * @des 自动生成客户编号
     * @return class CustomerBaseInfoVO
     * @version v1
     */
    @GetMapping("v1/generateNumber")
    public Result generateNumber() throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.generateCustomerNum();
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1,CustomerBaseInfoVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户基本信息
     *
     * @param customerBaseInfoTO 客户基本信息数据to
     * @des 添加客户基本信息
     * @return class CustomerBaseInfoVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCustomerBaseInfo(@Validated CustomerBaseInfoTO customerBaseInfoTO) throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.addCustomerBaseInfo(customerBaseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1,CustomerBaseInfoVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户基本
     *
     * @param customerBaseInfoTO 客户基本基本信息数据bo
     * @des 添加客户基本
     * @return class CustomerBaseInfoVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editCustomerBaseInfo(@Validated CustomerBaseInfoTO customerBaseInfoTO) throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.editCustomerBaseInfo(customerBaseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1,CustomerBaseInfoVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户基本信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCustomerBaseInfo(@PathVariable String id) throws ActException {
        try {
            customerBaseInfoAPI.deleteCustomerBaseInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户基本信息记录
     * @version v1
     */
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            customerBaseInfoAPI.congealCustomerBaseInfo(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户基本信息记录
     * @version v1
     */
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            customerBaseInfoAPI.thawCustomerBaseInfo(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户地区
     *
     * @des 获取客户地区集合
     * @return class String
     * @version v1
     */
    @GetMapping("v1/getArea")
    public Result getCusArea( ) throws ActException {
        try {
            List<String> areaList = customerBaseInfoAPI.getCustomerBaseInfoArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户名
     *
     * @des 获取客户名集合
     * @return class String
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getCusName( ) throws ActException {
        try {
            List<String> nameList = customerBaseInfoAPI.getCustomerBaseInfoName();
            return ActResult.initialize(nameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}