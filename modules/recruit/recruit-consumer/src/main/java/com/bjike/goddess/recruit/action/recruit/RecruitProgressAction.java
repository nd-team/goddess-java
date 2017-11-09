package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.recruit.api.RecruitDemandPlanAPI;
import com.bjike.goddess.recruit.bo.RecruitProgressBO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 招聘管理进度汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘管理进度汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recruitprogress")
public class RecruitProgressAction {
    @Autowired
    private RecruitDemandPlanAPI recruitDemandPlanAPI;

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

            Boolean isHasPermission = recruitDemandPlanAPI.guidePermission(guidePermissionTO);
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
     * 招聘管理日汇总
     *
     * @param time 招聘需求与计划dto
     * @return class RecruitProgressBO
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(String time) throws ActException {
        try {
            List<RecruitProgressBO> boList = recruitDemandPlanAPI.dayCollect(time);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class RecruitProgressBO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            List<RecruitProgressBO> boList = recruitDemandPlanAPI.weekCollect(year, month, week);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘管理月汇总
     *
     * @param year
     * @param month
     * @return class RecruitProgressBO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year, Integer month) throws ActException {
        try {
            List<RecruitProgressBO> boList = recruitDemandPlanAPI.monthCollect(year, month);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘管理季度汇总
     *
     * @param year
     * @param quarter
     * @return class RecruitProgressBO
     * @version v1
     */
    @GetMapping("v1/quarterCollect")
    public Result quarterCollect(Integer year, Integer quarter) throws ActException {
        try {
            List<RecruitProgressBO> boList = recruitDemandPlanAPI.quarterCollect(year, quarter);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘管理年汇总
     *
     * @param year
     * @return class RecruitProgressBO
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect(Integer year) throws ActException {
        try {
            List<RecruitProgressBO> boList = recruitDemandPlanAPI.yearCollect(year);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘管理累计汇总
     *
     * @param time 招聘需求与计划dto
     * @return class RecruitProgressBO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(String time) throws ActException {
        try {
            List<RecruitProgressBO> boList = recruitDemandPlanAPI.totalCollect(time);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}