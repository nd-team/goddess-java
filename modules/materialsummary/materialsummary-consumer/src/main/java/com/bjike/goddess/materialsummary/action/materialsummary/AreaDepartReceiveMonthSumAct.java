package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.AreaDepartReceiveMonthSumAPI;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveMonthSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveMonthSumDTO;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveMonthSumTO;
import com.bjike.goddess.materialsummary.vo.AreaDepartReceiveMonthSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区部门领用月汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areadepartreceivemonthsum")
public class AreaDepartReceiveMonthSumAct {

    @Autowired
    private AreaDepartReceiveMonthSumAPI areaDepartReceiveMonthSumAPI;

    /**
     * 根据id查询地区部门领用月汇总记录
     *
     * @param id 地区部门领用月汇总记录唯一标识
     * @return class AreaDepartReceiveMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areadepartreceivemonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AreaDepartReceiveMonthSumBO bo = areaDepartReceiveMonthSumAPI.findById(id);
            AreaDepartReceiveMonthSumVO vo = BeanTransform.copyProperties(bo, AreaDepartReceiveMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 地区部门领用月汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated AreaDepartReceiveMonthSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = areaDepartReceiveMonthSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 地区部门领用月汇总记录dto
     * @return class AreaDepartReceiveMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated AreaDepartReceiveMonthSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaDepartReceiveMonthSumBO> boList = areaDepartReceiveMonthSumAPI.list(dto);
            List<AreaDepartReceiveMonthSumVO> voList = BeanTransform.copyProperties(boList, AreaDepartReceiveMonthSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加地区部门领用月汇总记录
     *
     * @param to 地区部门领用月汇总记录to信息
     * @return class AreaDepartReceiveMonthSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) AreaDepartReceiveMonthSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AreaDepartReceiveMonthSumBO bo = areaDepartReceiveMonthSumAPI.save(to);
            AreaDepartReceiveMonthSumVO vo = BeanTransform.copyProperties(bo, AreaDepartReceiveMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除地区部门领用月汇总记录
     *
     * @param id 地区部门领用月汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            areaDepartReceiveMonthSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑地区部门领用月汇总记录
     *
     * @param to 地区部门领用月汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) AreaDepartReceiveMonthSumTO to, BindingResult result) throws ActException {
        try {
            areaDepartReceiveMonthSumAPI.update(to);
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
            List<AreaDepartReceiveMonthSumBO> listBO = areaDepartReceiveMonthSumAPI.summary();
            List<AreaDepartReceiveMonthSumVO> listVO = BeanTransform.copyProperties(listBO, AreaDepartReceiveMonthSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}