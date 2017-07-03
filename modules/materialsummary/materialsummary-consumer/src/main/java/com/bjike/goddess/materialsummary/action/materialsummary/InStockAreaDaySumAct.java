package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.InStockAreaDaySumAPI;
import com.bjike.goddess.materialsummary.bo.InStockAreaDaySumBO;
import com.bjike.goddess.materialsummary.dto.InStockAreaDaySumDTO;
import com.bjike.goddess.materialsummary.to.InStockAreaDaySumTO;
import com.bjike.goddess.materialsummary.vo.InStockAreaDaySumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 入库地区日汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:13 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("instockareadaysum")
public class InStockAreaDaySumAct {

    @Autowired
    private InStockAreaDaySumAPI inStockAreaDaySumAPI;

    /**
     * 根据id查询入库地区日汇总记录
     *
     * @param id 入库地区日汇总记录唯一标识
     * @return class InStockAreaDaySumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransferyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InStockAreaDaySumBO bo = inStockAreaDaySumAPI.findById(id);
            InStockAreaDaySumVO vo = BeanTransform.copyProperties(bo, InStockAreaDaySumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 入库地区日汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated InStockAreaDaySumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = inStockAreaDaySumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 入库地区日汇总记录dto
     * @return class InStockAreaDaySumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated InStockAreaDaySumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InStockAreaDaySumBO> boList = inStockAreaDaySumAPI.list(dto);
            List<InStockAreaDaySumVO> voList = BeanTransform.copyProperties(boList, InStockAreaDaySumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加入库地区日汇总记录
     *
     * @param to 入库地区日汇总记录to信息
     * @return class InStockAreaDaySumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) InStockAreaDaySumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InStockAreaDaySumBO bo = inStockAreaDaySumAPI.save(to);
            InStockAreaDaySumVO vo = BeanTransform.copyProperties(bo, InStockAreaDaySumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除入库地区日汇总记录
     *
     * @param id 入库地区日汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            inStockAreaDaySumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑入库地区日汇总记录
     *
     * @param to 入库地区日汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) InStockAreaDaySumTO to, BindingResult result) throws ActException {
        try {
            inStockAreaDaySumAPI.update(to);
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
            List<InStockAreaDaySumBO> listBO = inStockAreaDaySumAPI.summary();
            List<InStockAreaDaySumVO> listVO = BeanTransform.copyProperties(listBO, InStockAreaDaySumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}