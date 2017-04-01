package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CustomerDetailAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerDetailBO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.service.CustomerDetailSer;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import com.bjike.goddess.customer.vo.CusFamilyMemberVO;
import com.bjike.goddess.customer.vo.CustomerBaseInfoVO;
import com.bjike.goddess.customer.vo.CustomerDetailVO;
import com.bjike.goddess.customer.vo.CustomerLevelVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户详细信息
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.473 ]
 * @Description: [ 客户详细信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customer/customerdetail")
public class CustomerDetailAction {

    @Autowired
    private CustomerDetailAPI customerDetailAPI;


    /**
     *  客户详细列表总条数
     *
     * @param customerDetailDTO 客户详细信息dto
     * @des 获取所有客户详细信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CustomerDetailDTO customerDetailDTO) throws ActException {
        try {
            Long count = customerDetailAPI.countCustomerDetail(customerDetailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户详细列表
     *
     * @param customerDetailDTO 客户详细信息dto
     * @des 获取所有客户详细信息
     * @return class CustomerDetailVO
     * @version v1
     */
    @GetMapping("v1/listCustomerDetail")
    public Result findListCustomerDetail(CustomerDetailDTO customerDetailDTO) throws ActException {
        try {
            List<CustomerDetailVO> customerDetailVOList = new ArrayList<>();
            List<CustomerDetailBO> customerDetailBOList = customerDetailAPI.listCustomerDetail(customerDetailDTO);
            customerDetailBOList.stream().forEach(str->{
                CustomerLevelVO customerLevelVO = BeanTransform.copyProperties(str.getCustomerBaseInfoBO().getCustomerLevelBO() , CustomerLevelVO.class, true);
                CustomerBaseInfoVO customerBaseInfoVO = BeanTransform.copyProperties(str.getCustomerBaseInfoBO(), CustomerBaseInfoVO.class);
                customerBaseInfoVO.setCustomerLevelVO(customerLevelVO);
                CustomerDetailVO customerDetailVO = BeanTransform.copyProperties( str  , CustomerDetailVO.class, true);
                customerDetailVO.setCustomerBaseInfoVO(customerBaseInfoVO);
                customerDetailVOList.add( customerDetailVO );
            });
            return ActResult.initialize(customerDetailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户详细
     *
     * @param customerNum 客户编号
     * @des 根据客户编号获取单个客户详细
     * @return class CustomerDetailVO
     * @version v1
     */
    @GetMapping("v1/getInfoByCustomerNum")
    public Result getInfoByCustomerNum(String customerNum) throws ActException {
        try {
            CustomerDetailBO customerDetailBO1 = customerDetailAPI.getCustomerDetailByNum(customerNum);

            CustomerBaseInfoBO customerBaseInfoBO = customerDetailBO1.getCustomerBaseInfoBO();
            CustomerBaseInfoVO baseInfoVO = BeanTransform.copyProperties(customerBaseInfoBO.getCustomerLevelBO(),CustomerBaseInfoVO.class);
            CustomerLevelVO clevel = BeanTransform.copyProperties( customerBaseInfoBO.getCustomerLevelBO(), CustomerLevel.class);
            baseInfoVO.setCustomerLevelVO( clevel );
            List<CusFamilyMemberVO> family =  BeanTransform.copyProperties(customerDetailBO1.getCusFamilyMemberBOList(),CusFamilyMemberVO.class);

            CustomerDetailVO vo = BeanTransform.copyProperties(customerDetailBO1,CustomerDetailVO.class,true);
            vo.setCusFamilyMemberVOList(family);
            vo.setCustomerBaseInfoVO( baseInfoVO );

            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户详细
     *
     * @param customerDetailTO 客户详细基本信息数据to
     * @des 添加客户详细
     * @return class CustomerDetailVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCustomerDetail(@Validated CustomerDetailTO customerDetailTO, BindingResult bindingResult) throws ActException {
        try {
            CustomerDetailBO customerDetailBO1 = customerDetailAPI.addCustomerDetail(customerDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerDetailBO1,CustomerDetailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户详细
     *
     * @param customerDetailTO 客户详细基本信息数据bo
     * @des 添加客户详细
     * @return class CustomerDetailVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCustomerDetail(@Validated CustomerDetailTO customerDetailTO, BindingResult bindingResult) throws ActException {
        try {
            CustomerDetailBO customerDetailBO1 = customerDetailAPI.editCustomerDetail(customerDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerDetailBO1,CustomerDetailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户详细信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteEntryBasicInfo(@PathVariable String id) throws ActException {
        try {
            customerDetailAPI.deleteCustomerDetail(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出
     *
     * @param area 地区
     * @param customerName 客户名
     * @des 根据地区或客户名导出还不可以用
     * @version v1
     */
    @GetMapping("v1/exportInfo")
    public Result exportCustomerBasicInfo(String area ,String customerName) throws ActException {
            //TODO : tanghaixiang 2017-03-16 导出未做
//            customerDetailAPI.deleteCustomerDetail(id);
            return new ActResult("export success!");

    }


}