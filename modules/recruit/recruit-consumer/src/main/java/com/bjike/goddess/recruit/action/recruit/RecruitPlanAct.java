package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.RecruitPlanAPI;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import com.bjike.goddess.recruit.vo.NotEntryReasonVO;
import com.bjike.goddess.recruit.vo.RecruitPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 17:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruitPlan")
public class RecruitPlanAct {


    @Autowired
    private RecruitPlanAPI recruitPlanAPI;

    /**
     * 根据id查询招聘计划
     *
     * @param id 招聘计划唯一标识
     * @return class RecruitPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/recruitPlan/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecruitPlanBO bo = recruitPlanAPI.findById(id);
            RecruitPlanVO vo = BeanTransform.copyProperties(bo, RecruitPlanVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 招聘计划dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RecruitPlanDTO dto, BindingResult result) throws ActException {
        try {
            Long count = recruitPlanAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 招聘计划传输对象
     * @return class RecruitPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecruitPlanDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<RecruitPlanBO> boList = recruitPlanAPI.list(dto);
            List<RecruitPlanVO> voList = BeanTransform.copyProperties(boList, RecruitPlanVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招聘计划
     *
     * @param to 招聘计划to信息
     * @return class RecruitPlanVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RecruitPlanTO to, HttpServletRequest request) throws ActException {
        try {
            RecruitPlanBO bo = recruitPlanAPI.save(to);
            RecruitPlanVO vo = BeanTransform.copyProperties(bo, RecruitPlanVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招聘计划
     *
     * @param id 招聘计划唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recruitPlanAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招聘计划
     *
     * @param to 招聘计划to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RecruitPlanTO to) throws ActException {
        try {
            recruitPlanAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
