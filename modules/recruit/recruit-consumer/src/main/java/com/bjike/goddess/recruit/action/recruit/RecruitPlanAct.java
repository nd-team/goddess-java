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
import com.bjike.goddess.recruit.bo.CountBO;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import com.bjike.goddess.recruit.vo.CountVO;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = recruitPlanAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
     * @param dto 招聘计划dto
     * @return class RecruitPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecruitPlanDTO dto,HttpServletRequest request) throws ActException {
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
    public Result add(@Validated(value = {ADD.class}) RecruitPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RecruitPlanBO bo = recruitPlanAPI.save(to);
            RecruitPlanVO vo = BeanTransform.copyProperties(bo, RecruitPlanVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除招聘计划
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
    public Result edit(@Validated(value = {EDIT.class}) RecruitPlanTO to, BindingResult result) throws ActException {
        try {
            recruitPlanAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计划与实际对比
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countSituation")
    public Result countSituation(@Validated({RecruitPlanDTO.Situation.class}) RecruitPlanDTO dto, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(recruitPlanAPI.countSituation(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 日汇总
     *
     * @param dto dto
     * @return class CountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCount")
    public Result dayCount(@Validated({RecruitPlanDTO.DAY.class}) RecruitPlanDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CountBO> list = recruitPlanAPI.dayCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 周汇总
     *
     * @param dto dto
     * @return class CountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCount")
    public Result weekCount(@Validated({RecruitPlanDTO.WEEK.class}) RecruitPlanDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CountBO> list = recruitPlanAPI.weekCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param dto dto
     * @return class CountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCount")
    public Result monthCount(@Validated({RecruitPlanDTO.MONTH.class}) RecruitPlanDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CountBO> list = recruitPlanAPI.monthCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
