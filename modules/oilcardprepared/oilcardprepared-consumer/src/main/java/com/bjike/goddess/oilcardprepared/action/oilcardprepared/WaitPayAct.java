package com.bjike.goddess.oilcardprepared.action.oilcardprepared;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardprepared.api.WaitPayAPI;
import com.bjike.goddess.oilcardprepared.bo.ContrastBO;
import com.bjike.goddess.oilcardprepared.bo.WaitPayBO;
import com.bjike.goddess.oilcardprepared.dto.WaitPayDTO;
import com.bjike.goddess.oilcardprepared.to.GuidePermissionTO;
import com.bjike.goddess.oilcardprepared.to.WaitPayTO;
import com.bjike.goddess.oilcardprepared.vo.ContrastVO;
import com.bjike.goddess.oilcardprepared.vo.WaitPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitpay")
public class WaitPayAct {
    @Autowired
    private WaitPayAPI waitPayAPI;

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

            Boolean isHasPermission = waitPayAPI.guidePermission(guidePermissionTO);
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
     * 确认是否付款
     *
     * @param to 等待付款信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/confirmPay")
    public Result confirmPay(@Validated({EDIT.class}) WaitPayTO to, BindingResult result) throws ActException {
        try {
            waitPayAPI.confirmPay(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     等待付款分页信息
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      等待付款id
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/waitpay/{id}")
    public Result waitpay(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            WaitPayBO bo = waitPayAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count/{startTime}/{endTime}")
    public Result count(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.count(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 对比
     *
     * @param month   对比的月份
     * @param request 请求对象
     * @return class ContrastVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contrast/{month}")
    public Result contrast(@PathVariable Integer month, HttpServletRequest request) throws ActException {
        try {
            List<ContrastBO> list = waitPayAPI.contrast(month);
            return ActResult.initialize(BeanTransform.copyProperties(list, ContrastVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找已付款列表
     *
     * @param dto dto
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pays")
    public Result pays(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.pays(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找等待付款总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/waitCountSum")
    public Result waitCountSum(WaitPayDTO dto) throws ActException {
        try {
            return ActResult.initialize(waitPayAPI.waitCountSum(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找已付款总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/payCountSum")
    public Result payCountSum(WaitPayDTO dto) throws ActException {
        try {
            return ActResult.initialize(waitPayAPI.payCountSum(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}