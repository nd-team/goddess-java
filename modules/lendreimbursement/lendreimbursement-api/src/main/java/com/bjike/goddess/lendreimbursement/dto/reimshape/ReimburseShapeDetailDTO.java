package com.bjike.goddess.lendreimbursement.dto.reimshape;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.ReimShapeDetailTypeStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimburseShapeDetailStatus;
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
public class ReimburseShapeDetailDTO extends BaseDTO {

    public interface TESTYEARCON {
    }

    public interface TESTMONCON {
    }

    public interface TESTWEEKCON {
    }

    public interface TESTDAYCON {
    }

    public interface TESTQUARTCON {
    }

    /**
     * 详细汇总的标识(申报报销记录/待审核/待支付/已支付)
     */
    @NotNull(groups = {ReimburseShapeDetailDTO.TESTWEEKCON.class,ReimburseShapeDetailDTO.TESTMONCON.class,ReimburseShapeDetailDTO.TESTYEARCON.class} , message = "详细汇总的标识(待审核/待支付/已支付)不能为空")
    private ReimburseShapeDetailStatus detailStatus;

    /**
     * 汇总类型（年月周）
     */
    @NotNull(groups = {ReimburseShapeDetailDTO.TESTWEEKCON.class,ReimburseShapeDetailDTO.TESTMONCON.class,ReimburseShapeDetailDTO.TESTYEARCON.class} , message = "汇总类型不能为空")
    private ReimburseShapeStatus shapeStatus;

    /**
     * 年
     */
    @NotNull(groups = {ReimburseShapeDetailDTO.TESTWEEKCON.class,ReimburseShapeDetailDTO.TESTMONCON.class,ReimburseShapeDetailDTO.TESTYEARCON.class} , message = "年不能为空")
    private int year;

    /**
     * 月
     */
    @NotNull(groups = {ReimburseShapeDetailDTO.TESTWEEKCON.class,ReimburseShapeDetailDTO.TESTMONCON.class} , message = "月不能为空")
    private int month;

    /**
     * 周
     */
    @NotNull(groups = {ReimburseShapeDetailDTO.TESTWEEKCON.class} , message = "周不能为空")
    private int week;


    /**
     * 汇总数据（项目组/项目名称/地区）
     */
    @NotNull(groups = {ReimburseShapeDetailDTO.TESTWEEKCON.class,ReimburseShapeDetailDTO.TESTMONCON.class,ReimburseShapeDetailDTO.TESTYEARCON.class} , message = "汇总类型不能为空")
    private ReimShapeDetailTypeStatus detailTypeStatus;


    public ReimburseShapeDetailStatus getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(ReimburseShapeDetailStatus detailStatus) {
        this.detailStatus = detailStatus;
    }

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

    public ReimShapeDetailTypeStatus getDetailTypeStatus() {
        return detailTypeStatus;
    }

    public void setDetailTypeStatus(ReimShapeDetailTypeStatus detailTypeStatus) {
        this.detailTypeStatus = detailTypeStatus;
    }
}