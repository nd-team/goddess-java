package com.bjike.goddess.salaryconfirm.action.invoice;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.salaryconfirm.api.InvoiceSubmitAPI;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.excel.SonPermissionObject;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.InvoiceSubmitTO;
import com.bjike.goddess.salaryconfirm.vo.InvoiceSubmitVO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 上交发票
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 上交发票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("invoicesubmit")
public class InvoiceSubmitAct {

    @Autowired
    private InvoiceSubmitAPI invoiceSubmitAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = invoiceSubmitAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = invoiceSubmitAPI.guidePermission(guidePermissionTO);
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
     * 新增上交发票
     *
     * @param to 上交发票
     * @return class InvoiceSubmitVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) InvoiceSubmitTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            InvoiceSubmitVO voList = BeanTransform.copyProperties(invoiceSubmitAPI.add(to), InvoiceSubmitVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑上交发票
     *
     * @param to 上交发票
     * @return class InvoiceSubmitVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) InvoiceSubmitTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            InvoiceSubmitVO vo = BeanTransform.copyProperties(invoiceSubmitAPI.edit(to), InvoiceSubmitVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除上交发票
     *
     * @param id 上交发票ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            invoiceSubmitAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class InvoiceSubmitVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(InvoiceSubmitDTO dto) throws ActException {
        try {
            List<InvoiceSubmitVO> voList = BeanTransform.copyProperties(invoiceSubmitAPI.pageList(dto), InvoiceSubmitVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvoiceSubmitDTO dto) throws ActException {
        try {
            Long count = invoiceSubmitAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询上交发票
     *
     * @param id 上交发票id
     * @return class InvoiceSubmitVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InvoiceSubmitVO vo = BeanTransform.copyProperties(invoiceSubmitAPI.findById(id), InvoiceSubmitVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}