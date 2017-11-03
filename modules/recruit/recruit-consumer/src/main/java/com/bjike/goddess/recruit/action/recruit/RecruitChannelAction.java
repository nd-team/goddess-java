package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.recruit.api.RecruitDemandPlanAPI;
import com.bjike.goddess.recruit.bo.OptionBO;
import com.bjike.goddess.recruit.bo.OptionPieBO;
import com.bjike.goddess.recruit.bo.RecruitChannelBO;
import com.bjike.goddess.recruit.bo.RecruitDetailsBO;
import com.bjike.goddess.recruit.to.ChannelTO;
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
@RequestMapping("recruitchannel")
public class RecruitChannelAction {
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
     * 渠道汇总
     *
     * @param to 渠道汇总dto
     * @return class OptionBO
     * @version v1
     */
    @GetMapping("v1/channelCollect")
    public Result channelCollect(ChannelTO to) throws ActException {
        try {
            OptionBO boList = recruitDemandPlanAPI.channelCollect(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 渠道汇总
     *
     * @param to 渠道汇总dto
     * @return class OptionPieBO
     * @version v1
     */
    @GetMapping("v1/faceFigure")
    public Result faceFigure(ChannelTO to) throws ActException {
        try {
            OptionPieBO boList = recruitDemandPlanAPI.faceFigure(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 渠道汇总
     *
     * @param to 渠道汇总dto
     * @return class OptionPieBO
     * @version v1
     */
    @GetMapping("v1/entryFigure")
    public Result entryFigure(ChannelTO to) throws ActException {
        try {
            OptionPieBO boList = recruitDemandPlanAPI.entryFigure(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}