package com.bjike.goddess.carinfo.action.carinfo;

import com.bjike.goddess.carinfo.api.DriverInfoAPI;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.to.DriverInfoTO;
import com.bjike.goddess.carinfo.vo.DriverInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 司机信息管理
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:46 ]
 * @Description: [ 车辆信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("driverinfo")
public class DriverInfoAct {

    @Autowired
    private DriverInfoAPI driverInfoAPI;

    /**
     * 新增
     *
     * @param to 司机信息
     * @return class DriverInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DriverInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DriverInfoVO voList = BeanTransform.copyProperties(driverInfoAPI.save(to), DriverInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 司机信息
     * @return class DriverInfoVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DriverInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DriverInfoVO vo = BeanTransform.copyProperties(driverInfoAPI.edit(to), DriverInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id Id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            driverInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DriverInfoDTO dto) throws ActException {
        try {
            Long count = driverInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询司机信息
     *
     * @param id Id
     * @return class DriverInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DriverInfoVO vo = BeanTransform.copyProperties(driverInfoAPI.findById(id), DriverInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 查询条件或分页条件
     * @return class DriverInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(DriverInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DriverInfoVO> voList = BeanTransform.copyProperties(driverInfoAPI.pageList(dto), DriverInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param id id
     * @param suggest 审核意见
     * @param audit 审核结果
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/audit/{id}")
    public Result audit(@PathVariable String id,String suggest, Boolean audit) throws ActException {
        try {
            driverInfoAPI.audit(id,suggest,audit);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}