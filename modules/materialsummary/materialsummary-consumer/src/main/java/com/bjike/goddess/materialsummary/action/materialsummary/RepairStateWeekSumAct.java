package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.RepairStateWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.RepairStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateWeekSumDTO;
import com.bjike.goddess.materialsummary.to.RepairStateWeekSumTO;
import com.bjike.goddess.materialsummary.vo.RepairStateWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 维修状态周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:49 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("repairstateweeksum")
public class RepairStateWeekSumAct {

    @Autowired
    private RepairStateWeekSumAPI repairStateWeekSumAPI;

    /**
     * 根据id查询维修状态周汇总记录
     *
     * @param id 维修状态周汇总记录唯一标识
     * @return class RepairStateWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/repairstatemonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RepairStateWeekSumBO bo = repairStateWeekSumAPI.findById(id);
            RepairStateWeekSumVO vo = BeanTransform.copyProperties(bo, RepairStateWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 维修状态周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RepairStateWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = repairStateWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 维修状态周汇总记录dto
     * @return class RepairStateWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RepairStateWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RepairStateWeekSumBO> boList = repairStateWeekSumAPI.list(dto);
            List<RepairStateWeekSumVO> voList = BeanTransform.copyProperties(boList, RepairStateWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加维修状态周汇总记录
     *
     * @param to 维修状态周汇总记录to信息
     * @return class RepairStateWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RepairStateWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RepairStateWeekSumBO bo = repairStateWeekSumAPI.save(to);
            RepairStateWeekSumVO vo = BeanTransform.copyProperties(bo, RepairStateWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除维修状态周汇总记录
     *
     * @param id 维修状态周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            repairStateWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑维修状态周汇总记录
     *
     * @param to 维修状态周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RepairStateWeekSumTO to, BindingResult result) throws ActException {
        try {
            repairStateWeekSumAPI.update(to);
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
            List<RepairStateWeekSumBO> listBO = repairStateWeekSumAPI.summary();
            List<RepairStateWeekSumVO> listVO = BeanTransform.copyProperties(listBO, RepairStateWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}