package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.AreaTransferWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.AreaTransferWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferWeekSumDTO;
import com.bjike.goddess.materialsummary.to.AreaTransferWeekSumTO;
import com.bjike.goddess.materialsummary.vo.AreaTransferWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区调动周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:48 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areatransferweeksum")
public class AreaTransferWeekSumAct {

    @Autowired
    private AreaTransferWeekSumAPI areaTransferWeekSumAPI;

    /**
     * 根据id查询地区调动周汇总记录
     *
     * @param id 地区调动周汇总记录唯一标识
     * @return class AreaTransferWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransfermonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AreaTransferWeekSumBO bo = areaTransferWeekSumAPI.findById(id);
            AreaTransferWeekSumVO vo = BeanTransform.copyProperties(bo, AreaTransferWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 地区调动周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated AreaTransferWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = areaTransferWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 地区调动周汇总记录dto
     * @return class AreaTransferWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated AreaTransferWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaTransferWeekSumBO> boList = areaTransferWeekSumAPI.list(dto);
            List<AreaTransferWeekSumVO> voList = BeanTransform.copyProperties(boList, AreaTransferWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加地区调动周汇总记录
     *
     * @param to 地区调动周汇总记录to信息
     * @return class AreaTransferWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) AreaTransferWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AreaTransferWeekSumBO bo = areaTransferWeekSumAPI.save(to);
            AreaTransferWeekSumVO vo = BeanTransform.copyProperties(bo, AreaTransferWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除地区调动周汇总记录
     *
     * @param id 地区调动周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            areaTransferWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑地区调动周汇总记录
     *
     * @param to 地区调动周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) AreaTransferWeekSumTO to, BindingResult result) throws ActException {
        try {
            areaTransferWeekSumAPI.update(to);
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
            List<AreaTransferWeekSumBO> listBO = areaTransferWeekSumAPI.summary();
            List<AreaTransferWeekSumVO> listVO = BeanTransform.copyProperties(listBO, AreaTransferWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}