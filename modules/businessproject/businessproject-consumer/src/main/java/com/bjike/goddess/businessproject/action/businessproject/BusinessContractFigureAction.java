package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.businessproject.bo.BrokenOptionBO;
import com.bjike.goddess.businessproject.bo.BrokenOptionMakeBO;
import com.bjike.goddess.businessproject.bo.OptionBO;
import com.bjike.goddess.businessproject.bo.OptionMakeBO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 商务项目合同图表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-23 11:36 ]
 * @Description: [ 商务项目合同图表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesscontractfigure")
public class BusinessContractFigureAction extends BaseFileAction {
    @Autowired
    private BusinessContractAPI businessContractAPI;

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

            Boolean isHasPermission = businessContractAPI.guidePermission(guidePermissionTO);
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
     * 各地区合同规模数图表日汇总
     *
     * @param time
     * @des 各地区合同规模数图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayAreaScaleFigureCollect")
    public Result dayAreaScaleFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.dayAreaScaleFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各地区合同规模数图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekAreaScaleFigureCollect")
    public Result weekAreaScaleFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO bo = businessContractAPI.weekAreaScaleFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同规模数图形月份汇总
     *
     * @param year
     * @param month
     * @des 各地区合同规模数图形月份汇总
     * @version v1
     */
    @GetMapping("v1/monthAreaScaleFigureCollect")
    public Result monthAreaScaleFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionBO bo = businessContractAPI.monthAreaScaleFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各地区合同规模数图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterAreaScaleFigureCollect")
    public Result quarterAreaScaleFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionBO bo = businessContractAPI.quarterAreaScaleFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同规模数图表年汇总
     *
     * @param year
     * @des 各地区合同规模数图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearAreaScaleFigureCollect")
    public Result yearAreaScaleFigureCollect(Integer year) throws ActException {
        try {
            OptionBO bo = businessContractAPI.yearAreaScaleFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同规模数图表累计汇总
     *
     * @param time
     * @des 各地区合同规模数图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalAreaScaleFigureCollect")
    public Result totalAreaScaleFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.totalAreaScaleFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同规模数图表日汇总
     *
     * @param time
     * @des 各所属项目组合同规模数图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayProjectGroupScaleFigureCollect")
    public Result dayProjectGroupScaleFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.dayProjectGroupScaleFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各所属项目组合同规模数图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekProjectGroupScaleFigureCollect")
    public Result weekProjectGroupScaleFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO bo = businessContractAPI.weekProjectGroupScaleFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同规模数图表月汇总
     *
     * @param year
     * @param month
     * @des 各所属项目组合同规模数图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthProjectGroupScaleFigureCollect")
    public Result monthProjectGroupScaleFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionBO bo = businessContractAPI.monthProjectGroupScaleFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各所属项目组合同规模数图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterProjectGroupScaleFigureCollect")
    public Result quarterProjectGroupScaleFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionBO bo = businessContractAPI.quarterProjectGroupScaleFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同规模数图表年汇总
     *
     * @param year
     * @des 各所属项目组合同规模数图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearProjectGroupScaleFigureCollect")
    public Result yearProjectGroupScaleFigureCollect(Integer year) throws ActException {
        try {
            OptionBO bo = businessContractAPI.yearProjectGroupScaleFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同规模数图表累计汇总
     *
     * @param time
     * @des 各所属项目组合同规模数图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalProjectGroupScaleFigureCollect")
    public Result totalProjectGroupScaleFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.totalProjectGroupScaleFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同规模数图表日汇总
     *
     * @param time
     * @des 各所属项目组合同规模数图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayMajorScaleFigureCollect")
    public Result dayMajorScaleFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.dayMajorScaleFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各所属项目组合同规模数图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekMajorScaleFigureCollect")
    public Result weekMajorScaleFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO bo = businessContractAPI.weekMajorScaleFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同规模数图表月汇总
     *
     * @param year
     * @param month
     * @des 各所属项目组合同规模数图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthMajorScaleFigureCollect")
    public Result monthMajorScaleFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionBO bo = businessContractAPI.monthMajorScaleFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各所属项目组合同规模数图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterMajorScaleFigureCollect")
    public Result quarterMajorScaleFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionBO bo = businessContractAPI.quarterMajorScaleFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同规模数图表年汇总
     *
     * @param year
     * @des 各专业工期合同规模数图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearMajorScaleFigureCollect")
    public Result yearMajorScaleFigureCollect(Integer year) throws ActException {
        try {
            OptionBO bo = businessContractAPI.yearMajorScaleFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同规模数图表累计汇总
     *
     * @param time
     * @des 各专业工期合同规模数图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalMajorScaleFigureCollect")
    public Result totalMajorScaleFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.totalMajorScaleFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况金额图表日汇总
     *
     * @param time
     * @des 各地区合同立项情况金额图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayAreaMakeFigureCollect")
    public Result dayAreaMakeFigureCollect(String time) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.dayAreaMakeFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各地区合同立项情况金额图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekAreaMakeFigureCollect")
    public Result weekAreaMakeFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.weekAreaMakeFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况金额图表月份汇总
     *
     * @param year
     * @param month
     * @return class OptionMakeBO
     * @des 各地区合同立项情况金额图表月份汇总
     * @version v1
     */
    @GetMapping("v1/monthAreaMakeFigureCollect")
    public Result monthAreaMakeFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.monthAreaMakeFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各地区合同立项情况金额图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterAreaMakeFigureCollect")
    public Result quarterAreaMakeFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.quarterAreaMakeFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况金额图表年汇总
     *
     * @param year
     * @des 各地区合同立项情况金额图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearAreaMakeFigureCollect")
    public Result yearAreaMakeFigureCollect(Integer year) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.yearAreaMakeFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况金额图表累计汇总
     *
     * @param time
     * @des 各地区合同立项情况金额图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalAreaMakeFigureCollect")
    public Result totalAreaMakeFigureCollect(String time) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.totalAreaMakeFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况金额图表日汇总
     *
     * @param time
     * @des 各所属项目组合同立项情况金额图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayProjectGroupMakeFigureCollect")
    public Result dayProjectGroupMakeFigureCollect(String time) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.dayProjectGroupMakeFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各所属项目组合同立项情况金额图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekProjectGroupMakeFigureCollect")
    public Result weekProjectGroupMakeFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.weekProjectGroupMakeFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况金额图表月汇总
     *
     * @param year
     * @param month
     * @des 各所属项目组合同立项情况金额图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthProjectGroupMakeFigureCollect")
    public Result monthProjectGroupMakeFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.monthProjectGroupMakeFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各所属项目组合同立项情况金额图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterProjectGroupMakeFigureCollect")
    public Result quarterProjectGroupMakeFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.quarterProjectGroupMakeFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况金额图表年汇总
     *
     * @param year
     * @des 各所属项目组合同立项情况金额图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearProjectGroupMakeFigureCollect")
    public Result yearProjectGroupMakeFigureCollect(Integer year) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.yearProjectGroupMakeFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况金额图表累计汇总
     *
     * @param time
     * @des 各所属项目组合同立项情况金额图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalProjectGroupMakeFigureCollect")
    public Result totalProjectGroupMakeFigureCollect(String time) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.totalProjectGroupMakeFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况金额图表日汇总
     *
     * @param time
     * @des 各专业工期合同立项情况金额图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayMajorMakeFigureCollect")
    public Result dayMajorMakeFigureCollect(String time) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.dayMajorMakeFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各专业工期合同立项情况金额图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekMajorMakeFigureCollect")
    public Result weekMajorMakeFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.weekMajorMakeFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况金额图表月汇总
     *
     * @param year
     * @param month
     * @des 各专业工期合同立项情况金额图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthMajorMakeFigureCollect")
    public Result monthMajorMakeFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.monthMajorMakeFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各专业工期合同立项情况金额图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterMajorMakeFigureCollect")
    public Result quarterMajorMakeFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.quarterMajorMakeFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况金额图表年汇总
     *
     * @param year
     * @des 各专业工期合同立项情况金额图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearMajorMakeFigureCollect")
    public Result yearMajorMakeFigureCollect(Integer year) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.yearMajorMakeFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况金额图表累计汇总
     *
     * @param time
     * @des 各专业工期合同立项情况金额图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalMajorMakeFigureCollect")
    public Result totalMajorMakeFigureCollect(String time) throws ActException {
        try {
            OptionMakeBO bo = businessContractAPI.totalMajorMakeFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况图表日汇总
     *
     * @param time
     * @des 各地区合同立项情况图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayAreaMakeCaseFigureCollect")
    public Result dayAreaMakeCaseFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.dayAreaMakeCaseFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各地区合同立项情况图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekAreaMakeCaseFigureCollect")
    public Result weekAreaMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO bo = businessContractAPI.weekAreaMakeCaseFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @des 各地区合同立项情况图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthAreaMakeCaseFigureCollect")
    public Result monthAreaMakeCaseFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionBO bo = businessContractAPI.monthAreaMakeCaseFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各地区合同立项情况图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterAreaMakeCaseFigureCollect")
    public Result quarterAreaMakeCaseFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionBO bo = businessContractAPI.quarterAreaMakeCaseFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况图表年汇总
     *
     * @param year
     * @des 各地区合同立项情况图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearAreaMakeCaseFigureCollect")
    public Result yearAreaMakeCaseFigureCollect(Integer year) throws ActException {
        try {
            OptionBO bo = businessContractAPI.yearAreaMakeCaseFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各地区合同立项情况图表累计汇总
     *
     * @param time
     * @des 各地区合同立项情况图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalAreaMakeCaseFigureCollect")
    public Result totalAreaMakeCaseFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.totalAreaMakeCaseFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况图表日汇总
     *
     * @param time
     * @des 各所属项目组合同立项情况图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayProjectGroupMakeCaseFigureCollect")
    public Result dayProjectGroupMakeCaseFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.dayProjectGroupMakeCaseFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各所属项目组合同立项情况图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekProjectGroupMakeCaseFigureCollect")
    public Result weekProjectGroupMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO bo = businessContractAPI.weekProjectGroupMakeCaseFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @des 各所属项目组合同立项情况图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthProjectGroupMakeCaseFigureCollect")
    public Result monthProjectGroupMakeCaseFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionBO bo = businessContractAPI.monthProjectGroupMakeCaseFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各所属项目组合同立项情况图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterProjectGroupMakeCaseFigureCollect")
    public Result quarterProjectGroupMakeCaseFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionBO bo = businessContractAPI.quarterProjectGroupMakeCaseFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况图表年汇总
     *
     * @param year
     * @des 各所属项目组合同立项情况图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearProjectGroupMakeCaseFigureCollect")
    public Result yearProjectGroupMakeCaseFigureCollect(Integer year) throws ActException {
        try {
            OptionBO bo = businessContractAPI.yearProjectGroupMakeCaseFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各所属项目组合同立项情况图表累计汇总
     *
     * @param time
     * @des 各所属项目组合同立项情况图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalProjectGroupMakeCaseFigureCollect")
    public Result totalProjectGroupMakeCaseFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.totalProjectGroupMakeCaseFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况图表日汇总
     *
     * @param time
     * @des 各专业工期合同立项情况图表日汇总
     * @version v1
     */
    @GetMapping("v1/dayMajorMakeCaseFigureCollect")
    public Result dayMajorMakeCaseFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.dayMajorMakeCaseFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @des 各专业工期合同立项情况图表周汇总
     * @version v1
     */
    @GetMapping("v1/weekMajorMakeCaseFigureCollect")
    public Result weekMajorMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO bo = businessContractAPI.weekMajorMakeCaseFigureCollect(year, month, week);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @des 各专业工期合同立项情况图表月汇总
     * @version v1
     */
    @GetMapping("v1/monthMajorMakeCaseFigureCollect")
    public Result monthMajorMakeCaseFigureCollect(Integer year, Integer month) throws ActException {
        try {
            OptionBO bo = businessContractAPI.monthMajorMakeCaseFigureCollect(year, month);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @des 各专业工期合同立项情况图表季度汇总
     * @version v1
     */
    @GetMapping("v1/quarterMajorMakeCaseFigureCollect")
    public Result quarterMajorMakeCaseFigureCollect(Integer year, Integer quarter) throws ActException {
        try {
            OptionBO bo = businessContractAPI.quarterMajorMakeCaseFigureCollect(year, quarter);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况图表年汇总
     *
     * @param year
     * @des 各专业工期合同立项情况图表年汇总
     * @version v1
     */
    @GetMapping("v1/yearMajorMakeCaseFigureCollect")
    public Result yearMajorMakeCaseFigureCollect(Integer year) throws ActException {
        try {
            OptionBO bo = businessContractAPI.yearMajorMakeCaseFigureCollect(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各专业工期合同立项情况图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @des 各专业工期合同立项情况图表累计汇总
     * @version v1
     */
    @GetMapping("v1/totalMajorMakeCaseFigureCollect")
    public Result totalMajorMakeCaseFigureCollect(String time) throws ActException {
        try {
            OptionBO bo = businessContractAPI.totalMajorMakeCaseFigureCollect(time);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同规模数汇总
     *
     * @param year
     * @return class BrokenOptionBO
     * @des 合同规模数汇总
     * @version v1
     */
    @GetMapping("v1/scaleNum")
    public Result scaleNum(Integer year) throws ActException {
        try {
            BrokenOptionBO bo = businessContractAPI.scaleNum(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同立项情况金额汇总
     *
     * @param year
     * @return class BrokenOptionMakeBO
     * @des 合同立项情况金额汇总
     * @version v1
     */
    @GetMapping("v1/makeMoney")
    public Result makeMoney(Integer year) throws ActException {
        try {
            BrokenOptionMakeBO bo = businessContractAPI.makeMoney(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同立项情况汇总
     *
     * @param year
     * @return class BrokenOptionBO
     * @des 合同立项情况汇总
     * @version v1
     */
    @GetMapping("v1/makeCase")
    public Result makeCase(Integer year) throws ActException {
        try {
            BrokenOptionBO bo = businessContractAPI.makeCase(year);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}