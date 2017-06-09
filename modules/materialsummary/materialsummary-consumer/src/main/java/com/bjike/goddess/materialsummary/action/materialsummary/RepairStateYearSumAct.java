package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.RepairStateYearSumAPI;
import com.bjike.goddess.materialsummary.bo.RepairStateYearSumBO;
import com.bjike.goddess.materialsummary.dto.RepairStateYearSumDTO;
import com.bjike.goddess.materialsummary.to.RepairStateYearSumTO;
import com.bjike.goddess.materialsummary.vo.RepairStateYearSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 维修状态年汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 01:48 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("repairstateyearsum")
public class RepairStateYearSumAct {

    @Autowired
    private RepairStateYearSumAPI repairStateYearSumAPI;

    /**
     * 根据id查询维修状态年汇总记录
     *
     * @param id 维修状态年汇总记录唯一标识
     * @return class RepairStateYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/repairstatemonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RepairStateYearSumBO bo = repairStateYearSumAPI.findById(id);
            RepairStateYearSumVO vo = BeanTransform.copyProperties(bo, RepairStateYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 维修状态年汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RepairStateYearSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = repairStateYearSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 维修状态年汇总记录dto
     * @return class RepairStateYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RepairStateYearSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RepairStateYearSumBO> boList = repairStateYearSumAPI.list(dto);
            List<RepairStateYearSumVO> voList = BeanTransform.copyProperties(boList, RepairStateYearSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加维修状态年汇总记录
     *
     * @param to 维修状态年汇总记录to信息
     * @return class RepairStateYearSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RepairStateYearSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RepairStateYearSumBO bo = repairStateYearSumAPI.save(to);
            RepairStateYearSumVO vo = BeanTransform.copyProperties(bo, RepairStateYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除维修状态年汇总记录
     *
     * @param id 维修状态年汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            repairStateYearSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑维修状态年汇总记录
     *
     * @param to 维修状态年汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RepairStateYearSumTO to, BindingResult result) throws ActException {
        try {
            repairStateYearSumAPI.update(to);
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
            List<RepairStateYearSumBO> listBO = repairStateYearSumAPI.summary();
            List<RepairStateYearSumVO> listVO = BeanTransform.copyProperties(listBO, RepairStateYearSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}