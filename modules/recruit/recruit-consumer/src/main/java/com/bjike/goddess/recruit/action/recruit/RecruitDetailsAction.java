package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.recruit.api.RecruitDemandPlanAPI;
import com.bjike.goddess.recruit.bo.RecruitDetailsBO;
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
 * 招聘管理详情汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘管理进度汇总 ]
 * @Version: [ v1.0.招聘管理详情汇总 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recruitdetails")
public class RecruitDetailsAction {
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
     * 招聘详情日汇总
     *
     * @param time 招聘需求与计划dto
     * @return class RecruitDetailsBO
     * @version v1
     */
    @GetMapping("v1/dayRecruit")
    public Result dayRecruit(String time) throws ActException {
        try {
            List<RecruitDetailsBO> boList = recruitDemandPlanAPI.dayRecruit(time);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘详情周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class RecruitDetailsBO
     * @version v1
     */
    @GetMapping("v1/weekRecruit")
    public Result weekRecruit(Integer year, Integer month, Integer week) throws ActException {
        try {
            List<RecruitDetailsBO> boList = recruitDemandPlanAPI.weekRecruit(year, month, week);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招聘详情月汇总
     *
     * @param year
     * @param month
     * @return class RecruitDetailsBO
     * @version v1
     */
    @GetMapping("v1/monthRecruit")
    public Result monthRecruit(Integer year, Integer month) throws ActException {
        try {
            List<RecruitDetailsBO> boList = recruitDemandPlanAPI.monthRecruit(year, month);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有岗位
     *
     * @version v1
     */
    @GetMapping("v1/getPosition")
    public Result getPosition() throws ActException {
        try {
            List<String> list = recruitDemandPlanAPI.getPosition();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}