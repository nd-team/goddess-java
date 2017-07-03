package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.InStockSoruceMonthSumAPI;
import com.bjike.goddess.materialsummary.bo.InStockSoruceMonthSumBO;
import com.bjike.goddess.materialsummary.dto.InStockSoruceMonthSumDTO;
import com.bjike.goddess.materialsummary.to.InStockSoruceMonthSumTO;
import com.bjike.goddess.materialsummary.vo.InStockSoruceMonthSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 入库来源月汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:06 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("instocksorucemonthsum")
public class InStockSoruceMonthSumAct {

    @Autowired
    private InStockSoruceMonthSumAPI inStockSoruceMonthSumAPI;

    /**
     * 根据id查询入库来源月汇总记录
     *
     * @param id 入库来源月汇总记录唯一标识
     * @return class InStockSoruceMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransferyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InStockSoruceMonthSumBO bo = inStockSoruceMonthSumAPI.findById(id);
            InStockSoruceMonthSumVO vo = BeanTransform.copyProperties(bo, InStockSoruceMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 入库来源月汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated InStockSoruceMonthSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = inStockSoruceMonthSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 入库来源月汇总记录dto
     * @return class InStockSoruceMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated InStockSoruceMonthSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InStockSoruceMonthSumBO> boList = inStockSoruceMonthSumAPI.list(dto);
            List<InStockSoruceMonthSumVO> voList = BeanTransform.copyProperties(boList, InStockSoruceMonthSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加入库来源月汇总记录
     *
     * @param to 入库来源月汇总记录to信息
     * @return class InStockSoruceMonthSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) InStockSoruceMonthSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InStockSoruceMonthSumBO bo = inStockSoruceMonthSumAPI.save(to);
            InStockSoruceMonthSumVO vo = BeanTransform.copyProperties(bo, InStockSoruceMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除入库来源月汇总记录
     *
     * @param id 入库来源月汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            inStockSoruceMonthSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑入库来源月汇总记录
     *
     * @param to 入库来源月汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) InStockSoruceMonthSumTO to, BindingResult result) throws ActException {
        try {
            inStockSoruceMonthSumAPI.update(to);
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
            List<InStockSoruceMonthSumBO> listBO = inStockSoruceMonthSumAPI.summary();
            List<InStockSoruceMonthSumVO> listVO = BeanTransform.copyProperties(listBO, InStockSoruceMonthSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}