package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CusPermissionAPI;
import com.bjike.goddess.customer.api.CustomerDetailAPI;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.CustomerDetailBO;
import com.bjike.goddess.customer.dto.CustomerDetailDTO;
import com.bjike.goddess.customer.entity.CustomerDetail;
import com.bjike.goddess.customer.entity.CustomerLevel;
import com.bjike.goddess.customer.to.CustomerDetailTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.vo.CusFamilyMemberVO;
import com.bjike.goddess.customer.vo.CustomerBaseInfoVO;
import com.bjike.goddess.customer.vo.CustomerDetailVO;
import com.bjike.goddess.customer.vo.CustomerLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@RequestMapping("customerdetail")
public class CustomerDetailAction  extends BaseFileAction {

    @Autowired
    private CustomerDetailAPI customerDetailAPI;
    @Autowired
    private CusPermissionAPI cusPermissionAPI;


    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = customerDetailAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户详细列表总条数
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
     * @return class CustomerDetailVO
     * @des 获取所有客户详细信息
     * @version v1
     */
    @GetMapping("v1/listCustomerDetail")
    public Result findListCustomerDetail(CustomerDetailDTO customerDetailDTO) throws ActException {
        try {

            List<CustomerDetailVO> customerDetailVOList = new ArrayList<>();
            List<CustomerDetailBO> customerDetailBOList = customerDetailAPI.listCustomerDetail(customerDetailDTO);

            if (customerDetailBOList == null) {
                return ActResult.initialize(null);
            } else {
                customerDetailBOList.stream().forEach(str -> {
                    CustomerLevelVO customerLevelVO = BeanTransform.copyProperties(str.getCustomerBaseInfoBO().getCustomerLevelBO(), CustomerLevelVO.class, true);
                    CustomerBaseInfoVO customerBaseInfoVO = BeanTransform.copyProperties(str.getCustomerBaseInfoBO(), CustomerBaseInfoVO.class);
                    customerBaseInfoVO.setCustomerLevelVO(customerLevelVO);
                    CustomerDetailVO customerDetailVO = BeanTransform.copyProperties(str, CustomerDetailVO.class, true);
                    customerDetailVO.setCustomerBaseInfoVO(customerBaseInfoVO);

                    List<CusFamilyMemberVO> cusFamilyMemberVOList = BeanTransform.copyProperties( str.getCusFamilyMemberBOList(), CusFamilyMemberVO.class);
                    customerDetailVO.setCusFamilyMemberVOList( cusFamilyMemberVOList );
                    customerDetailVOList.add(customerDetailVO);
                });
                return ActResult.initialize(customerDetailVOList);
            }

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取客户详细
     *
     * @param customerNum 客户编号
     * @return class CustomerDetailVO
     * @des 根据客户编号获取单个客户详细
     * @version v1
     */
    @GetMapping("v1/getInfoByCustomerNum")
    public Result getInfoByCustomerNum(String customerNum) throws ActException {
        try {
            CustomerDetailBO customerDetailBO1 = customerDetailAPI.getCustomerDetailByNum(customerNum);

            CustomerBaseInfoBO customerBaseInfoBO = customerDetailBO1.getCustomerBaseInfoBO();
            CustomerBaseInfoVO baseInfoVO = BeanTransform.copyProperties(customerBaseInfoBO, CustomerBaseInfoVO.class);
            CustomerLevelVO clevel = BeanTransform.copyProperties(customerBaseInfoBO.getCustomerLevelBO(), CustomerLevelVO.class);
            baseInfoVO.setCustomerLevelVO(clevel);
            List<CusFamilyMemberVO> family = BeanTransform.copyProperties(customerDetailBO1.getCusFamilyMemberBOList(), CusFamilyMemberVO.class);

            CustomerDetailVO vo = BeanTransform.copyProperties(customerDetailBO1, CustomerDetailVO.class, true);
            vo.setCusFamilyMemberVOList(family);
            vo.setCustomerBaseInfoVO(baseInfoVO);

            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户详细
     *
     * @param customerDetailTO 客户详细基本信息数据to
     * @return class CustomerDetailVO
     * @des 添加客户详细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCustomerDetail(@Validated CustomerDetailTO customerDetailTO, BindingResult bindingResult) throws ActException {
        try {

            CustomerDetailBO customerDetailBO1 = customerDetailAPI.addCustomerDetail(customerDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerDetailBO1, CustomerDetailVO.class));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
//        return null;
    }


    /**
     * 编辑客户详细
     *
     * @param customerDetailTO 客户详细基本信息数据bo
     * @return class CustomerDetailVO
     * @des 添加客户详细
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCustomerDetail(@Validated CustomerDetailTO customerDetailTO, BindingResult bindingResult) throws ActException {
        try {

            CustomerDetailBO customerDetailBO1 = customerDetailAPI.editCustomerDetail(customerDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(customerDetailBO1, CustomerDetailVO.class, true));

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
    @LoginAuth
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
     * 多选或不选导出
     *
     * @param customerDetailDTO  地区或客户名
     * @des 根据地区或客户名导出
     * @version v1
     */
    @GetMapping("v1/exportInfo")
    public Result exportCustomerBasicInfo(CustomerDetailDTO customerDetailDTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "客户详细信息.xlsx";
            super.writeOutFile(response, customerDetailAPI.exportInfo( customerDetailDTO ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }

    }


}