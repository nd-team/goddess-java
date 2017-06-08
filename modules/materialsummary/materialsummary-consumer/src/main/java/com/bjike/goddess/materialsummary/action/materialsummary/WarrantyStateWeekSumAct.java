package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.WarrantyStateWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.WarrantyStateWeekSumBO;
import com.bjike.goddess.materialsummary.dto.WarrantyStateWeekSumDTO;
import com.bjike.goddess.materialsummary.to.WarrantyStateWeekSumTO;
import com.bjike.goddess.materialsummary.vo.WarrantyStateWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 保修状态周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 02:50 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("warrantystateweeksum")
public class WarrantyStateWeekSumAct {

    @Autowired
    private WarrantyStateWeekSumAPI warrantyStateWeekSumAPI;

    /**
     * 根据id查询保修状态周汇总记录
     *
     * @param id 保修状态周汇总记录唯一标识
     * @return class WarrantyStateWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/transfertypeyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            WarrantyStateWeekSumBO bo = warrantyStateWeekSumAPI.findById(id);
            WarrantyStateWeekSumVO vo = BeanTransform.copyProperties(bo, WarrantyStateWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 保修状态周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated WarrantyStateWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = warrantyStateWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 保修状态周汇总记录dto
     * @return class WarrantyStateWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated WarrantyStateWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<WarrantyStateWeekSumBO> boList = warrantyStateWeekSumAPI.list(dto);
            List<WarrantyStateWeekSumVO> voList = BeanTransform.copyProperties(boList, WarrantyStateWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加保修状态周汇总记录
     *
     * @param to 保修状态周汇总记录to信息
     * @return class WarrantyStateWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) WarrantyStateWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            WarrantyStateWeekSumBO bo = warrantyStateWeekSumAPI.save(to);
            WarrantyStateWeekSumVO vo = BeanTransform.copyProperties(bo, WarrantyStateWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除保修状态周汇总记录
     *
     * @param id 保修状态周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            warrantyStateWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑保修状态周汇总记录
     *
     * @param to 保修状态周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) WarrantyStateWeekSumTO to, BindingResult result) throws ActException {
        try {
            warrantyStateWeekSumAPI.update(to);
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
            List<WarrantyStateWeekSumBO> listBO = warrantyStateWeekSumAPI.summary();
            List<WarrantyStateWeekSumVO> listVO = BeanTransform.copyProperties(listBO, WarrantyStateWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}