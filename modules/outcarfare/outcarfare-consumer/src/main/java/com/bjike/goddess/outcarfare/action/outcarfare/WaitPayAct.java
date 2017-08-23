package com.bjike.goddess.outcarfare.action.outcarfare;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.outcarfare.api.WaitPayAPI;
import com.bjike.goddess.outcarfare.bo.ArrivalCountBO;
import com.bjike.goddess.outcarfare.bo.CarUserCountBO;
import com.bjike.goddess.outcarfare.bo.DriverCountBO;
import com.bjike.goddess.outcarfare.bo.WaitPayBO;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.WaitPayTO;
import com.bjike.goddess.outcarfare.vo.ArrivalCountVO;
import com.bjike.goddess.outcarfare.vo.CarUserCountVO;
import com.bjike.goddess.outcarfare.vo.DriverCountVO;
import com.bjike.goddess.outcarfare.vo.WaitPayVO;
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
 * @Date: [ 2017-05-05 03:11 ]
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
     * 添加
     *
     * @param to      等待付款信息
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({WaitPayTO.TestAdd.class}) WaitPayTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            WaitPayBO bo = waitPayAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      等待付款信息
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated({WaitPayTO.TestEdit.class}) WaitPayTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            WaitPayBO bo = waitPayAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, WaitPayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 确认付款
     *
     * @param to 等待付款信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/pay")
    public Result pay(@Validated({EDIT.class}) WaitPayTO to, BindingResult result) throws ActException {
        try {
            waitPayAPI.pay(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 等待付款id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款列表
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
     * 司机汇总
     *
     * @param request 请求对象
     * @return class DriverCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/driverCount")
    public Result driverCount(HttpServletRequest request) throws ActException {
        try {
            List<DriverCountBO> list = waitPayAPI.driverCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, DriverCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param request 请求对象
     * @return class ArrivalCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/arrivalCount")
    public Result arrivalCount(HttpServletRequest request) throws ActException {
        try {
            List<ArrivalCountBO> list = waitPayAPI.arrivalCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用车人汇总
     *
     * @param request 请求对象
     * @return class CarUserCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/carUserCount")
    public Result carUserCount(HttpServletRequest request) throws ActException {
        try {
            List<CarUserCountBO> list = waitPayAPI.carUserCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, CarUserCountVO.class, request));
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