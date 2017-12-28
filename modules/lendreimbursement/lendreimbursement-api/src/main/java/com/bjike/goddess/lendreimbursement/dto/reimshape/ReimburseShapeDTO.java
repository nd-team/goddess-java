package com.bjike.goddess.lendreimbursement.dto.reimshape;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneSelectStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimburseShapeStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 报销图形数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 报销图形数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseShapeDTO extends BaseDTO {

    public interface TESTYEARCON{}
    public interface TESTMONCON{}
    public interface TESTWEEKCON{}
    public interface TESTDAYCON{}
    public interface TESTQUARTCON{}

    /**
     * 汇总状态(日周月年)
     */
    @NotNull(groups = {ReimburseShapeDTO.TESTWEEKCON.class,ReimburseShapeDTO.TESTMONCON.class,ReimburseShapeDTO.TESTYEARCON.class,ReimburseShapeDTO.TESTDAYCON.class} , message = "汇总状态不能为空")
    private ReimburseShapeStatus reimburseShapeStatus;
    /**
     * 年
     */
    @NotNull(groups = {ReimburseShapeDTO.TESTWEEKCON.class,ReimburseShapeDTO.TESTMONCON.class,ReimburseShapeDTO.TESTYEARCON.class} , message = "年不能为空")
    private int year;

    /**
     * 月
     */
    @NotNull(groups = {ReimburseShapeDTO.TESTWEEKCON.class,ReimburseShapeDTO.TESTMONCON.class} , message = "月不能为空")
    private int month;

    /**
     * 周
     */
    @NotNull(groups = {ReimburseShapeDTO.TESTWEEKCON.class} , message = "周不能为空")
    private int week;
    /**
     * 季度
     */
    @NotNull(groups = {ReimburseShapeDTO.TESTQUARTCON.class} , message = "季度不能为空")
    private int quart;

    /**
     * 用户名
     */
    @NotBlank(groups = {ReimburseShapeDTO.TESTWEEKCON.class,ReimburseShapeDTO.TESTMONCON.class,ReimburseShapeDTO.TESTYEARCON.class,ReimburseShapeDTO.TESTDAYCON.class} , message = "用户名不能为空")
    private String userName;

    /**
     * 日汇总的时间，格式为2017-10-23
     */
    @NotBlank(groups = {ReimburseShapeDTO.TESTDAYCON.class} , message = "日汇总的时间不能为空,格式为2017-10-23")
    private String time;

    public ReimburseShapeStatus getReimburseShapeStatus() {
        return reimburseShapeStatus;
    }

    public void setReimburseShapeStatus(ReimburseShapeStatus reimburseShapeStatus) {
        this.reimburseShapeStatus = reimburseShapeStatus;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getQuart() {
        return quart;
    }

    public void setQuart(int quart) {
        this.quart = quart;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}