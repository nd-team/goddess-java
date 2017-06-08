package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.InStockAreaYearSumAPI;
import com.bjike.goddess.materialsummary.bo.InStockAreaYearSumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaYearSumDTO;
import com.bjike.goddess.materialsummary.to.InStockAreaYearSumTO;
import com.bjike.goddess.materialsummary.vo.InStockAreaYearSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 入库地区年汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("instockareayearsum")
public class InStockAreaYearSumAct {

    @Autowired
    private InStockAreaYearSumAPI inStockAreaYearSumAPI;

    /**
     * 根据id查询入库地区年汇总记录
     *
     * @param id 入库地区年汇总记录唯一标识
     * @return class InStockAreaYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransferyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InStockAreaYearSumBO bo = inStockAreaYearSumAPI.findById(id);
            InStockAreaYearSumVO vo = BeanTransform.copyProperties(bo, InStockAreaYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 入库地区年汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated InStockAreaYearSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = inStockAreaYearSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 入库地区年汇总记录dto
     * @return class InStockAreaYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated InStockAreaYearSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InStockAreaYearSumBO> boList = inStockAreaYearSumAPI.list(dto);
            List<InStockAreaYearSumVO> voList = BeanTransform.copyProperties(boList, InStockAreaYearSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加入库地区年汇总记录
     *
     * @param to 入库地区年汇总记录to信息
     * @return class InStockAreaYearSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) InStockAreaYearSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InStockAreaYearSumBO bo = inStockAreaYearSumAPI.save(to);
            InStockAreaYearSumVO vo = BeanTransform.copyProperties(bo, InStockAreaYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除入库地区年汇总记录
     *
     * @param id 入库地区年汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            inStockAreaYearSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑入库地区年汇总记录
     *
     * @param to 入库地区年汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) InStockAreaYearSumTO to, BindingResult result) throws ActException {
        try {
            inStockAreaYearSumAPI.update(to);
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
            List<InStockAreaYearSumBO> listBO = inStockAreaYearSumAPI.summary();
            List<InStockAreaYearSumVO> listVO = BeanTransform.copyProperties(listBO, InStockAreaYearSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}