package com.bjike.goddess.firmreward.action.firmreward;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.api.RewardIndicatorAPI;
import com.bjike.goddess.firmreward.bo.RewardIndicatorBO;
import com.bjike.goddess.firmreward.dto.RewardIndicatorDTO;
import com.bjike.goddess.firmreward.to.RewardIndicatorTO;
import com.bjike.goddess.firmreward.vo.RewardIndicatorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 奖励指标
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("rewardindicator")
public class RewardIndicatorAct {

    @Autowired
    private RewardIndicatorAPI rewardIndicatorAPI;

    /**
     * 根据id查询奖励指标
     *
     * @param id 奖励指标唯一标识
     * @return class RewardIndicatorVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/rewardindicator/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RewardIndicatorBO bo = rewardIndicatorAPI.findById(id);
            RewardIndicatorVO vo = BeanTransform.copyProperties(bo, RewardIndicatorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 奖励指标dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated RewardIndicatorDTO dto, BindingResult result) throws ActException {
        try {
            Long count = rewardIndicatorAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询奖励指标
     *
     * @param dto 奖励指标dto
     * @return class RewardIndicatorVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated RewardIndicatorDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RewardIndicatorBO> boList = rewardIndicatorAPI.list(dto);
            List<RewardIndicatorVO> voList = BeanTransform.copyProperties(boList, RewardIndicatorVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加奖励指标
     *
     * @param to 奖励指标to
     * @return class RewardIndicatorVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RewardIndicatorTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RewardIndicatorBO bo = rewardIndicatorAPI.save(to);
            RewardIndicatorVO vo = BeanTransform.copyProperties(bo, RewardIndicatorVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除奖励指标
     *
     * @param id 奖励指标唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rewardIndicatorAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑奖励指标
     *
     * @param to 奖励指标to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated RewardIndicatorTO to, BindingResult result) throws ActException {
        try {
            rewardIndicatorAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}