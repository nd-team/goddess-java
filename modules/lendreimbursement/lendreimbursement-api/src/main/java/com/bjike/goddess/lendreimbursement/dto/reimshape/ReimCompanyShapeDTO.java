package com.bjike.goddess.lendreimbursement.dto.reimshape;

import com.bjike.goddess.common.api.dto.BaseDTO;
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
public class ReimCompanyShapeDTO extends BaseDTO {

    public interface TESTYEARCON{}
    public interface TESTMONCON{}
    public interface TESTWEEKCON{}

    /**
     * 汇总类型
     */
    @NotNull(groups = {ReimCompanyShapeDTO.TESTWEEKCON.class,ReimCompanyShapeDTO.TESTMONCON.class,ReimCompanyShapeDTO.TESTYEARCON.class} , message = "汇总类型不能为空")
    private ReimburseShapeStatus shapeStatus;

    /**
     * 年
     */
    @NotNull(groups = {ReimCompanyShapeDTO.TESTWEEKCON.class,ReimCompanyShapeDTO.TESTMONCON.class,ReimCompanyShapeDTO.TESTYEARCON.class} , message = "年不能为空")
    private int year;

    /**
     * 月
     */
    @NotNull(groups = {ReimCompanyShapeDTO.TESTWEEKCON.class,ReimCompanyShapeDTO.TESTMONCON.class} , message = "月不能为空")
    private int month;

    /**
     * 周
     */
    @NotNull(groups = {ReimCompanyShapeDTO.TESTWEEKCON.class} , message = "周不能为空")
    private int week;

    public ReimburseShapeStatus getShapeStatus() {
        return shapeStatus;
    }

    public void setShapeStatus(ReimburseShapeStatus shapeStatus) {
        this.shapeStatus = shapeStatus;
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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}