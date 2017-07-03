package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.MaterialStatusWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.MaterialStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.to.MaterialStatusWeekSumTO;
import com.bjike.goddess.materialsummary.vo.MaterialStatusWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资状态周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:21 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialstatusweeksum")
public class MaterialStatusWeekSumAct {

    @Autowired
    private MaterialStatusWeekSumAPI materialStatusWeekSumAPI;

    /**
     * 根据id查询物资状态周汇总记录
     *
     * @param id 物资状态周汇总记录唯一标识
     * @return class MaterialStatusWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialclassifyyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialStatusWeekSumBO bo = materialStatusWeekSumAPI.findById(id);
            MaterialStatusWeekSumVO vo = BeanTransform.copyProperties(bo, MaterialStatusWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资状态周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialStatusWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = materialStatusWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 物资状态周汇总记录dto
     * @return class MaterialStatusWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialStatusWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MaterialStatusWeekSumBO> boList = materialStatusWeekSumAPI.list(dto);
            List<MaterialStatusWeekSumVO> voList = BeanTransform.copyProperties(boList, MaterialStatusWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资状态周汇总记录
     *
     * @param to 物资状态周汇总记录to信息
     * @return class MaterialStatusWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) MaterialStatusWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialStatusWeekSumBO bo = materialStatusWeekSumAPI.save(to);
            MaterialStatusWeekSumVO vo = BeanTransform.copyProperties(bo, MaterialStatusWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资状态周汇总记录
     *
     * @param id 物资状态周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialStatusWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资状态周汇总记录
     *
     * @param to 物资状态周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) MaterialStatusWeekSumTO to, BindingResult result) throws ActException {
        try {
            materialStatusWeekSumAPI.update(to);
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
            List<MaterialStatusWeekSumBO> listBO = materialStatusWeekSumAPI.summary();
            List<MaterialStatusWeekSumVO> listVO = BeanTransform.copyProperties(listBO, MaterialStatusWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}