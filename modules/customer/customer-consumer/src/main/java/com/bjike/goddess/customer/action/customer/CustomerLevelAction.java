package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CustomerLevelAPI;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.service.CustomerLevelSer;
import com.bjike.goddess.customer.to.CustomerLevelTO;
import com.bjike.goddess.customer.vo.CustomerLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客户级别
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T17:00:40.875 ]
 * @Description: [ 客户级别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customer/customerlevel")
public class CustomerLevelAction {

    @Autowired
    private CustomerLevelAPI customerLevelAPI;

    /**
     *  客户等级列表总条数
     *
     * @param customerBaseInfoDTO  客户等级信息dto
     * @des 获取所有客户等级信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomerLevelDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = customerLevelAPI.countCustomerLevel(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户等级列表
     *
     * @param customerLevelDTO 客户等级信息dto
     * @des 获取所有客户等级信息
     * @return  class CustomerLevelVO
     * @version v1
     */
    @GetMapping("v1/listCustomerLevel")
    public Result findListCustomerLevel(CustomerLevelDTO customerLevelDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CustomerLevelVO> customerLevelVOList = BeanTransform.copyProperties(
                    customerLevelAPI.listCustomerLevel(customerLevelDTO), CustomerLevelVO.class, true);
            return ActResult.initialize(customerLevelVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户等级
     *
     * @param customerLevelTO 客户等级基本信息数据to
     * @des 添加客户等级
     * @return  class CustomerLevelVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCustomerLevel(@Validated CustomerLevelTO customerLevelTO, BindingResult bindingResult) throws ActException {
        try {
            CustomerLevelBO customerLevelBO1 = customerLevelAPI.addCustomerLevel(customerLevelTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerLevelBO1,CustomerLevelVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户等级
     *
     * @param customerLevelTO 客户等级基本信息数据bo
     * @des 添加客户等级
     * @return  class CustomerLevelVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCustomerLevel(@Validated CustomerLevelTO customerLevelTO) throws ActException {
        try {
            CustomerLevelBO customerLevelBO1 = customerLevelAPI.editCustomerLevel(customerLevelTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerLevelBO1,CustomerLevelVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户等级信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCustomerLevel(@PathVariable String id) throws ActException {
        try {
            customerLevelAPI.deleteCustomerLevel(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 查找客户等级
     *
     * @param name 客户等级名name
     * @des 根据id查找某个客户等级信息
     * @return  class CustomerLevelVO
     * @version v1
     */
    @GetMapping("v1/getCustomerLevel")
    public Result findOneCustomerLevel( String name) throws ActException {
        try {
            CustomerLevelVO customerLevelVO = BeanTransform.copyProperties(
                    customerLevelAPI.getCustomerLevelByName(name), CustomerLevelVO.class, true);
            return ActResult.initialize(customerLevelVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户等级记录
     * @version v1
     */
    @PostMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            customerLevelAPI.congealCustomerLevel(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException("冻结失败："+e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户等级记录
     * @version v1
     */
    @PostMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            customerLevelAPI.thawCustomerLevel(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException("冻结失败："+e.getMessage());
        }
    }

}