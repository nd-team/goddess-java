package com.bjike.goddess.staffmove.action.staffmove;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.staffmove.api.StaffMoveImplementAAPI;
import com.bjike.goddess.staffmove.bo.OptionBO;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 人员调动意愿情况图形
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况图形 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffmovefigure")
public class StaffMoveFigureAction {
    @Autowired
    private StaffMoveImplementAAPI staffMoveImplementAAPI;

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

            Boolean isHasPermission = staffMoveImplementAAPI.guidePermission(guidePermissionTO);
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
     * 人员调动管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @des 人员调动管理周汇总
     * @version v1
     */
    @GetMapping("v1/weekStaffFigure")
    public Result weekStaffFigure(Integer year, Integer month, Integer week) throws ActException {
        try {
            OptionBO optionBO = staffMoveImplementAAPI.weekStaffFigure(year, month, week);
            return ActResult.initialize(optionBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员调动管理月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @des 人员调动管理月汇总
     * @version v1
     */
    @GetMapping("v1/monthStaffFigure")
    public Result monthStaffFigure(Integer year, Integer month) throws ActException {
        try {
            OptionBO optionBO = staffMoveImplementAAPI.monthStaffFigure(year, month);
            return ActResult.initialize(optionBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员调动管理累计汇总
     *
     * @param time
     * @return class OptionBO
     * @des 人员调动管理累计汇总
     * @version v1
     */
    @GetMapping("v1/totalStaffFigure")
    public Result totalStaffFigure(String time) throws ActException {
        try {
            OptionBO optionBO = staffMoveImplementAAPI.totalStaffFigure(time);
            return ActResult.initialize(optionBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前月有几周
     *
     * @param year  年份
     * @param month 月份
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findWeek/{year}/{month}")
    public Result findWeek(@PathVariable Integer year, @PathVariable Integer month) throws ActException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= weekNum; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年份
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/year")
    public Result year() throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            Integer year = LocalDate.now().getYear();
            for (int i = year - 5; i < year + 5; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

}