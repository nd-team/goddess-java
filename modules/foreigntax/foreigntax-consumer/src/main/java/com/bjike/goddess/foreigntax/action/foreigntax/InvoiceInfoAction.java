package com.bjike.goddess.foreigntax.action.foreigntax;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.AccountInfoManagementAPI;
import com.bjike.goddess.foreigntax.api.InvoiceInfoAPI;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.bo.InvoiceInfoBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.dto.InvoiceInfoDTO;
import com.bjike.goddess.foreigntax.entity.IncomeInvoice;
import com.bjike.goddess.foreigntax.entity.InvoiceInfo;
import com.bjike.goddess.foreigntax.to.*;
import com.bjike.goddess.foreigntax.vo.AccountInfoManagementVO;
import com.bjike.goddess.foreigntax.vo.InvoiceInfoVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发票基本登记
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:36 ]
 * @Description: [ 发票基本登记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("invoiceinfo")
public class InvoiceInfoAction {
    @Autowired
    private InvoiceInfoAPI invoiceInfoAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = invoiceInfoAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发票基本登记列表总条数
     *
     * @param dto 发票基本登记dto
     * @des 获取所有发票基本登记总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvoiceInfoDTO dto) throws ActException {
        try {
            Long count = invoiceInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个发票基本登记
     *
     * @param id
     * @return class InvoiceInfoVO
     * @des 获取一个发票基本登记
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            InvoiceInfoBO bo = invoiceInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InvoiceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发票基本登记列表
     *
     * @param dto 发票基本登记dto
     * @return class InvoiceInfoVO
     * @des 获取所有发票基本登记
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvoiceInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<InvoiceInfoVO> invoiceInfoVOS = BeanTransform.copyProperties
                    (invoiceInfoAPI.list(dto), InvoiceInfoVO.class, request);
            return ActResult.initialize(invoiceInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加发票基本登记
     *
     * @param to 发票基本登记数据to
     * @return class InvoiceInfoVO
     * @des 添加发票基本登记
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(InvoiceInfoTO.TestAdd.class) InvoiceInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            InvoiceInfoBO bo = invoiceInfoAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,InvoiceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑发票基本登记
     *
     * @param to 发票基本登记数据to
     * @return class InvoiceInfoVO
     * @des 编辑发票基本登记
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(InvoiceInfoTO.TestEdit.class) InvoiceInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            InvoiceInfoBO bo = invoiceInfoAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,InvoiceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除发票基本登记
     *
     * @param id 用户id
     * @des 根据用户id删除发票基本登记记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            invoiceInfoAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}