package com.bjike.goddess.foreigntax.action.foreigntax;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.InvoiceReceiveAPI;
import com.bjike.goddess.foreigntax.bo.InvoiceReceiveBO;
import com.bjike.goddess.foreigntax.dto.InvoiceReceiveDTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.InvoiceReceiveTO;
import com.bjike.goddess.foreigntax.vo.InvoiceReceiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发票领用
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("invoicereceive")
public class InvoiceReceiveAction {
    @Autowired
    private InvoiceReceiveAPI invoiceReceiveAPI;

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

            Boolean isHasPermission = invoiceReceiveAPI.guidePermission(guidePermissionTO);
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
     * 发票领用列表总条数
     *
     * @param dto 发票领用dto
     * @des 获取所有发票领用总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvoiceReceiveDTO dto) throws ActException {
        try {
            Long count = invoiceReceiveAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个发票领用
     *
     * @param id
     * @return class InvoiceReceiveVO
     * @des 获取一个发票领用
     * @version v1
     */
    @GetMapping("v1/receive/{id}")
    public Result receive(@PathVariable String id) throws ActException {
        try {
            InvoiceReceiveBO bo = invoiceReceiveAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InvoiceReceiveVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发票领用列表
     *
     * @param dto 发票领用dto
     * @return class InvoiceReceiveVO
     * @des 获取所有发票领用
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvoiceReceiveDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<InvoiceReceiveVO> invoiceReceiveVOS = BeanTransform.copyProperties
                    (invoiceReceiveAPI.list(dto), InvoiceReceiveVO.class, request);
            return ActResult.initialize(invoiceReceiveVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加发票领用
     *
     * @param to 发票领用数据to
     * @return class InvoiceReceiveVO
     * @des 添加发票领用
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvoiceReceiveTO to, BindingResult bindingResult) throws ActException {
        try {
            InvoiceReceiveBO bo = invoiceReceiveAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InvoiceReceiveVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑发票领用
     *
     * @param to 发票领用数据to
     * @return class InvoiceReceiveVO
     * @des 编辑发票领用
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) InvoiceReceiveTO to, BindingResult bindingResult) throws ActException {
        try {
            InvoiceReceiveBO bo = invoiceReceiveAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InvoiceReceiveVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除发票领用
     *
     * @param id 用户id
     * @des 根据用户id删除发票领用记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            invoiceReceiveAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}