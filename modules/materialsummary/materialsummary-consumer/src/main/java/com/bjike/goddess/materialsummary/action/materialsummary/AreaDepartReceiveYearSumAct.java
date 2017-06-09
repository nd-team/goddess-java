package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.AreaDepartReceiveYearSumAPI;
import com.bjike.goddess.materialsummary.bo.AreaDepartReceiveYearSumBO;
import com.bjike.goddess.materialsummary.dto.AreaDepartReceiveYearSumDTO;
import com.bjike.goddess.materialsummary.to.AreaDepartReceiveYearSumTO;
import com.bjike.goddess.materialsummary.vo.AreaDepartReceiveYearSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区部门领用年汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:27 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areadepartreceiveyearsum")
public class AreaDepartReceiveYearSumAct {

    @Autowired
    private AreaDepartReceiveYearSumAPI areaDepartReceiveWeekSumAPI;

    /**
     * 根据id查询地区部门领用年汇总记录
     *
     * @param id 地区部门领用年汇总记录唯一标识
     * @return class AreaDepartReceiveYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areadepartreceivemonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AreaDepartReceiveYearSumBO bo = areaDepartReceiveWeekSumAPI.findById(id);
            AreaDepartReceiveYearSumVO vo = BeanTransform.copyProperties(bo, AreaDepartReceiveYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 地区部门领用年汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated AreaDepartReceiveYearSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = areaDepartReceiveWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 地区部门领用年汇总记录dto
     * @return class AreaDepartReceiveYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated AreaDepartReceiveYearSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaDepartReceiveYearSumBO> boList = areaDepartReceiveWeekSumAPI.list(dto);
            List<AreaDepartReceiveYearSumVO> voList = BeanTransform.copyProperties(boList, AreaDepartReceiveYearSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加地区部门领用年汇总记录
     *
     * @param to 地区部门领用年汇总记录to信息
     * @return class AreaDepartReceiveYearSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) AreaDepartReceiveYearSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AreaDepartReceiveYearSumBO bo = areaDepartReceiveWeekSumAPI.save(to);
            AreaDepartReceiveYearSumVO vo = BeanTransform.copyProperties(bo, AreaDepartReceiveYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除地区部门领用年汇总记录
     *
     * @param id 地区部门领用年汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            areaDepartReceiveWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑地区部门领用年汇总记录
     *
     * @param to 地区部门领用年汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) AreaDepartReceiveYearSumTO to, BindingResult result) throws ActException {
        try {
            areaDepartReceiveWeekSumAPI.update(to);
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
            List<AreaDepartReceiveYearSumBO> listBO = areaDepartReceiveWeekSumAPI.summary();
            List<AreaDepartReceiveYearSumVO> listVO = BeanTransform.copyProperties(listBO, AreaDepartReceiveYearSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}