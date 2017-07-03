package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.InStockAreaWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.InStockAreaWeekSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaWeekSumDTO;
import com.bjike.goddess.materialsummary.to.InStockAreaWeekSumTO;
import com.bjike.goddess.materialsummary.vo.InStockAreaWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 入库地区周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:10 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("instockareaweeksum")
public class InStockAreaWeekSumAct {

    @Autowired
    private InStockAreaWeekSumAPI inStockAreaWeekSumAPI;

    /**
     * 根据id查询入库地区周汇总记录
     *
     * @param id 入库地区周汇总记录唯一标识
     * @return class InStockAreaWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransferyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InStockAreaWeekSumBO bo = inStockAreaWeekSumAPI.findById(id);
            InStockAreaWeekSumVO vo = BeanTransform.copyProperties(bo, InStockAreaWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 入库地区周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated InStockAreaWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = inStockAreaWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 入库地区周汇总记录dto
     * @return class InStockAreaWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated InStockAreaWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InStockAreaWeekSumBO> boList = inStockAreaWeekSumAPI.list(dto);
            List<InStockAreaWeekSumVO> voList = BeanTransform.copyProperties(boList, InStockAreaWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加入库地区周汇总记录
     *
     * @param to 入库地区周汇总记录to信息
     * @return class InStockAreaWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) InStockAreaWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InStockAreaWeekSumBO bo = inStockAreaWeekSumAPI.save(to);
            InStockAreaWeekSumVO vo = BeanTransform.copyProperties(bo, InStockAreaWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除入库地区周汇总记录
     *
     * @param id 入库地区周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            inStockAreaWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑入库地区周汇总记录
     *
     * @param to 入库地区周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) InStockAreaWeekSumTO to, BindingResult result) throws ActException {
        try {
            inStockAreaWeekSumAPI.update(to);
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
            List<InStockAreaWeekSumBO> listBO = inStockAreaWeekSumAPI.summary();
            List<InStockAreaWeekSumVO> listVO = BeanTransform.copyProperties(listBO, InStockAreaWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}