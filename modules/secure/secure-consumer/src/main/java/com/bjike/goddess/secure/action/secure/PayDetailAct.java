package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.PayDetailAPI;
import com.bjike.goddess.secure.bo.PayDetailBO;
import com.bjike.goddess.secure.dto.PayDetailDTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.PayDetailTO;
import com.bjike.goddess.secure.vo.PayDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 缴费比例明细
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 12:55 ]
 * @Description: [ 缴费比例明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("paydetail")
public class PayDetailAct {
    @Autowired
    private PayDetailAPI payDetailAPI;

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

            Boolean isHasPermission = payDetailAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @param to      缴费比例信息
     * @param request 请求对象
     * @return class PayDetailVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) PayDetailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            PayDetailBO bo = payDetailAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PayDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 缴费比例信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) PayDetailTO to, BindingResult result) throws ActException {
        try {
            payDetailAPI.edit(to);
            return new ActResult("更新成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 缴费比例id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            payDetailAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     缴费比例分页信息
     * @param request 请求对象
     * @return class PayDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PayDetailDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<PayDetailBO> list = payDetailAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, PayDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      缴费比例id
     * @param request 请求对象
     * @return class PayDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/paydetail/{id}")
    public Result paydetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PayDetailBO bo = payDetailAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PayDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PayDetailDTO dto) throws ActException {
        try {
            return ActResult.initialize(payDetailAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}