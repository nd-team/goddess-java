package com.bjike.goddess.equipmentprepared.action.equipmentprepared;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.equipmentprepared.api.WaitPayAPI;
import com.bjike.goddess.equipmentprepared.bo.PayCountBO;
import com.bjike.goddess.equipmentprepared.bo.WaitPayBO;
import com.bjike.goddess.equipmentprepared.dto.WaitPayDTO;
import com.bjike.goddess.equipmentprepared.to.WaitPayTO;
import com.bjike.goddess.equipmentprepared.vo.PayCountVO;
import com.bjike.goddess.equipmentprepared.vo.WaitPayVO;
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
 * @Date: [ 2017-05-13 02:27 ]
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
     * 确认付款
     *
     * @param to 等待付款信息
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/confirmPay")
    public Result confirmPay(@Validated({EDIT.class}) WaitPayTO to, BindingResult result) throws ActException {
        try {
            waitPayAPI.confirmPay(to);
            return new ActResult("付款成功!");
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
     * 导出（按时间选择数据）
     *
     * @param year    年份
     * @param month   月份
     * @param request 请求对象
     * @return class WaitPayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/export/{year}/{month}")
    public Result export(@PathVariable Integer year, @PathVariable Integer month, HttpServletRequest request) throws ActException {
        try {
            List<WaitPayBO> list = waitPayAPI.export(year, month);
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
     * 部门汇总
     *
     * @param request 请求对象
     * @return class PayCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/departmentCount")
    public Result departmentCount(HttpServletRequest request) throws ActException {
        try {
            List<PayCountBO> list = waitPayAPI.departmentCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, PayCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设备名称汇总
     *
     * @param request 请求对象
     * @return class PayCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/deviceNameCount")
    public Result deviceNameCount(HttpServletRequest request) throws ActException {
        try {
            List<PayCountBO> list = waitPayAPI.deviceNameCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, PayCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param request 请求对象
     * @return class PayCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areaCount")
    public Result areaCount(HttpServletRequest request) throws ActException {
        try {
            List<PayCountBO> list = waitPayAPI.areaCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, PayCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款列表
     *
     * @param request 请求对象
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
     * 等待付款总记录数
     *
     * @param dto     dto
     * @param request 请求对象
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/waitCountSum")
    public Result waitCountSum(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            Long sum = waitPayAPI.waitCountSum(dto);
            return ActResult.initialize(sum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款总记录数
     *
     * @param dto     dto
     * @param request 请求对象
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/payCountSum")
    public Result payCountSum(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            Long sum = waitPayAPI.payCountSum(dto);
            return ActResult.initialize(sum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}