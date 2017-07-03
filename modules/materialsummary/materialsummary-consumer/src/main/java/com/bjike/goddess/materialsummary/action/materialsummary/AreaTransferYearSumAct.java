package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.AreaTransferYearSumAPI;
import com.bjike.goddess.materialsummary.bo.AreaTransferYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferYearSumDTO;
import com.bjike.goddess.materialsummary.to.AreaTransferYearSumTO;
import com.bjike.goddess.materialsummary.vo.AreaTransferYearSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区调动年汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:47 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areatransferyearsum")
public class AreaTransferYearSumAct {

    @Autowired
    private AreaTransferYearSumAPI areaTransferYearSumAPI;

    /**
     * 根据id查询地区调动年汇总记录
     *
     * @param id 地区调动年汇总记录唯一标识
     * @return class AreaTransferYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransferyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AreaTransferYearSumBO bo = areaTransferYearSumAPI.findById(id);
            AreaTransferYearSumVO vo = BeanTransform.copyProperties(bo, AreaTransferYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 地区调动年汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated AreaTransferYearSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = areaTransferYearSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 地区调动年汇总记录dto
     * @return class AreaTransferYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated AreaTransferYearSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaTransferYearSumBO> boList = areaTransferYearSumAPI.list(dto);
            List<AreaTransferYearSumVO> voList = BeanTransform.copyProperties(boList, AreaTransferYearSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加地区调动年汇总记录
     *
     * @param to 地区调动年汇总记录to信息
     * @return class AreaTransferYearSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) AreaTransferYearSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AreaTransferYearSumBO bo = areaTransferYearSumAPI.save(to);
            AreaTransferYearSumVO vo = BeanTransform.copyProperties(bo, AreaTransferYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除地区调动年汇总记录
     *
     * @param id 地区调动年汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            areaTransferYearSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑地区调动年汇总记录
     *
     * @param to 地区调动年汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) AreaTransferYearSumTO to, BindingResult result) throws ActException {
        try {
            areaTransferYearSumAPI.update(to);
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
            List<AreaTransferYearSumBO> listBO = areaTransferYearSumAPI.summary();
            List<AreaTransferYearSumVO> listVO = BeanTransform.copyProperties(listBO, AreaTransferYearSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}