package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.RepairStateMonthSumAPI;
import com.bjike.goddess.materialsummary.bo.RepairStateMonthSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateMonthSumDTO;
import com.bjike.goddess.materialsummary.to.RepairStateMonthSumTO;
import com.bjike.goddess.materialsummary.vo.RepairStateMonthSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 维修状态月汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("repairstatemonthsum")
public class RepairStateMonthSumAct {

    @Autowired
    private RepairStateMonthSumAPI repairStateMonthSumAPI;

    /**
     * 根据id查询维修状态月汇总记录
     *
     * @param id 维修状态月汇总记录唯一标识
     * @return class RepairStateMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/repairstatemonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RepairStateMonthSumBO bo = repairStateMonthSumAPI.findById(id);
            RepairStateMonthSumVO vo = BeanTransform.copyProperties(bo, RepairStateMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 维修状态月汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RepairStateMonthSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = repairStateMonthSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 维修状态月汇总记录dto
     * @return class RepairStateMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RepairStateMonthSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RepairStateMonthSumBO> boList = repairStateMonthSumAPI.list(dto);
            List<RepairStateMonthSumVO> voList = BeanTransform.copyProperties(boList, RepairStateMonthSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加维修状态月汇总记录
     *
     * @param to 维修状态月汇总记录to信息
     * @return class RepairStateMonthSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RepairStateMonthSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RepairStateMonthSumBO bo = repairStateMonthSumAPI.save(to);
            RepairStateMonthSumVO vo = BeanTransform.copyProperties(bo, RepairStateMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除维修状态月汇总记录
     *
     * @param id 维修状态月汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            repairStateMonthSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑维修状态月汇总记录
     *
     * @param to 维修状态月汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RepairStateMonthSumTO to, BindingResult result) throws ActException {
        try {
            repairStateMonthSumAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * @param request
     * @return
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summary")
    public Result summary(HttpServletRequest request) throws ActException {
        try {
            List<RepairStateMonthSumBO> listBO = repairStateMonthSumAPI.summary();
            List<RepairStateMonthSumVO> listVO = BeanTransform.copyProperties(listBO, RepairStateMonthSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}