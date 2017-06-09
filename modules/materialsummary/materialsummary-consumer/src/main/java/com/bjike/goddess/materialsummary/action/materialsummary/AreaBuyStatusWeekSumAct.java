package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.AreaBuyStatusWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.AreaBuyStatusWeekSumBO;
import com.bjike.goddess.materialsummary.dto.AreaBuyStatusWeekSumDTO;
import com.bjike.goddess.materialsummary.to.AreaBuyStatusWeekSumTO;
import com.bjike.goddess.materialsummary.vo.AreaBuyStatusWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区购买情况周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:54 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areabuystatusweeksum")
public class AreaBuyStatusWeekSumAct {

    @Autowired
    private AreaBuyStatusWeekSumAPI areaBuyStatusWeekSumAPI;

    /**
     * 根据id查询地区购买情况周汇总记录
     *
     * @param id 地区购买情况周汇总记录唯一标识
     * @return class AreaBuyStatusWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areabuystatusweeksum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AreaBuyStatusWeekSumBO bo = areaBuyStatusWeekSumAPI.findById(id);
            AreaBuyStatusWeekSumVO vo = BeanTransform.copyProperties(bo, AreaBuyStatusWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 地区购买情况周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated AreaBuyStatusWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = areaBuyStatusWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 地区购买情况周汇总记录dto
     * @return class AreaBuyStatusWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated AreaBuyStatusWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaBuyStatusWeekSumBO> boList = areaBuyStatusWeekSumAPI.list(dto);
            List<AreaBuyStatusWeekSumVO> voList = BeanTransform.copyProperties(boList, AreaBuyStatusWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加地区购买情况周汇总记录
     *
     * @param to 地区购买情况周汇总记录to信息
     * @return class AreaBuyStatusWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) AreaBuyStatusWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AreaBuyStatusWeekSumBO bo = areaBuyStatusWeekSumAPI.save(to);
            AreaBuyStatusWeekSumVO vo = BeanTransform.copyProperties(bo, AreaBuyStatusWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除地区购买情况周汇总记录
     *
     * @param id 地区购买情况周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            areaBuyStatusWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑地区购买情况周汇总记录
     *
     * @param to 地区购买情况周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) AreaBuyStatusWeekSumTO to, BindingResult result) throws ActException {
        try {
            areaBuyStatusWeekSumAPI.update(to);
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
            List<AreaBuyStatusWeekSumBO> listBO = areaBuyStatusWeekSumAPI.summary();
            List<AreaBuyStatusWeekSumVO> listVO = BeanTransform.copyProperties(listBO, AreaBuyStatusWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}