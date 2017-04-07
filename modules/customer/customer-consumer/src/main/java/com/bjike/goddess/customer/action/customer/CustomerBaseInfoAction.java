package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.vo.CustomerBaseInfoVO;
import com.bjike.goddess.customer.vo.CustomerLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * 客户基本列表总条数
     *
     * @param customerBaseInfoDTO 客户基本信息dto
     * @des 获取所有客户基本信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomerBaseInfoDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = customerBaseInfoAPI.countCustomerBaseInfo(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
            List<CustomerBaseInfoBO> customerBaseInfoBOList = customerBaseInfoAPI.listCustomerBaseInfo(customerBaseInfoDTO);
            List<CustomerBaseInfoVO> customerBaseInfoVOList = new ArrayList<>();
            customerBaseInfoBOList.stream().forEach(str->{
                CustomerLevelVO customerLevelVO = BeanTransform.copyProperties(str.getCustomerLevelBO() , CustomerLevelVO.class, true);
                CustomerBaseInfoVO customerBaseInfoVO = BeanTransform.copyProperties(str, CustomerBaseInfoVO.class);
                customerBaseInfoVO.setCustomerLevelVO(customerLevelVO);
                customerBaseInfoVOList.add( customerBaseInfoVO );
            });
            return ActResult.initialize(customerBaseInfoVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自动编号
     *
     * @return class CustomerBaseInfoVO
     * @des 自动生成客户编号
     * @version v1
     */
    @GetMapping("v1/generateNumber")
    public Result generateNumber() throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.generateCustomerNum();
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1, CustomerBaseInfoVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户基本信息
     *
     * @param customerBaseInfoTO 客户基本信息数据to
     * @return class CustomerBaseInfoVO
     * @des 添加客户基本信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCustomerBaseInfo(@Validated CustomerBaseInfoTO customerBaseInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.addCustomerBaseInfo(customerBaseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1, CustomerBaseInfoVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户基本
     *
     * @param customerBaseInfoTO 客户基本基本信息数据bo
     * @return class CustomerBaseInfoVO
     * @des 添加客户基本
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCustomerBaseInfo(@Validated CustomerBaseInfoTO customerBaseInfoTO) throws ActException {
        try {
            CustomerBaseInfoBO customerBaseInfoBO1 = customerBaseInfoAPI.editCustomerBaseInfo(customerBaseInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerBaseInfoBO1, CustomerBaseInfoVO.class, true));
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
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户基本信息记录
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            customerBaseInfoAPI.congealCustomerBaseInfo(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException("冻结失败：" + e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户基本信息记录
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            customerBaseInfoAPI.thawCustomerBaseInfo(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException("解冻失败：" + e.getMessage());
        }
    }

    /**
     * 获取客户编号
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取客户编号集合
     * @version v1
     */
    @GetMapping("v1/getCusNum")
    public Result getCusNum() throws ActException {
        try {
            List<String> areaList = customerBaseInfoAPI.getCustomerBaseInfoCusNum();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户地区
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取客户地区集合
     * @version v1
     */
    @GetMapping("v1/getArea")
    public Result getCusArea() throws ActException {
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
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回地区数组'}
     * @des 获取客户名集合
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getCusName() throws ActException {
        try {
            List<String> nameList = customerBaseInfoAPI.getCustomerBaseInfoName();
            return ActResult.initialize(nameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取单个客户
     *
     * @param customerNum customerNum
     * @return class CustomerBaseInfoVO
     * @des 根据客户编号查询客户基本信息
     * @version v1
     */
    @GetMapping("v1/getCustomer")
    public Result getCustomer(String customerNum) throws ActException {
        try {
            CustomerBaseInfoBO bo = customerBaseInfoAPI.getCustomerInfoByNum(customerNum);
            CustomerLevelVO customerLevelVO =  BeanTransform.copyProperties(bo.getCustomerLevelBO(),CustomerLevelVO.class);
            CustomerBaseInfoVO customerBaseInfoVO = BeanTransform.copyProperties(bo, CustomerBaseInfoVO.class);
            customerBaseInfoVO.setCustomerLevelVO( customerLevelVO );
            return ActResult.initialize(customerBaseInfoVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取行业数组
     *
     * @return {name:'List<string>',type:'List<string>',defaultValue:'',description:'返回行业数组'}
     * @des 获取客户编号集合
     * @version v1
     */
    @GetMapping("v1/getWorks")
    public Result getWorks() throws ActException {
        try {
            List<String> workList = customerBaseInfoAPI.getCustomerBaseInfoWorks();
            return ActResult.initialize(workList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}