package com.bjike.goddess.lendreimbursement.dto.reimshape;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.ReimShapeDetailTypeStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimburseShapeStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 借款报销混合图形数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 借款报销混合图形数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendMixReimSelfShapeDTO extends BaseDTO {

    public interface TESTYEARCON{}
    public interface TESTMONCON{}
    public interface TESTWEEKCON{}
    public interface TESTDAYCON{}

    /**
     * 汇总状态(周月年)
     */
    @NotNull(groups = {LendMixReimSelfShapeDTO.TESTWEEKCON.class,LendMixReimSelfShapeDTO.TESTMONCON.class,LendMixReimSelfShapeDTO.TESTYEARCON.class,LendMixReimSelfShapeDTO.TESTDAYCON.class} , message = "汇总状态不能为空")
    private ReimburseShapeStatus reimburseShapeStatus;
    /**
     * 汇总类型(个人/地区/项目组/项目名称)
     */
    @NotNull(groups = {LendMixReimSelfShapeDTO.TESTWEEKCON.class,LendMixReimSelfShapeDTO.TESTMONCON.class,LendMixReimSelfShapeDTO.TESTYEARCON.class,LendMixReimSelfShapeDTO.TESTDAYCON.class} , message = "汇总状态不能为空")
    private ReimShapeDetailTypeStatus typeStatus;
    /**
     * 年
     */
    @NotNull(groups = {LendMixReimSelfShapeDTO.TESTWEEKCON.class,LendMixReimSelfShapeDTO.TESTMONCON.class,LendMixReimSelfShapeDTO.TESTYEARCON.class} , message = "年不能为空")
    private int year;

    /**
     * 月
     */
    @NotNull(groups = {LendMixReimSelfShapeDTO.TESTWEEKCON.class,LendMixReimSelfShapeDTO.TESTMONCON.class} , message = "月不能为空")
    private int month;

    /**
     * 周
     */
    @NotNull(groups = {LendMixReimSelfShapeDTO.TESTWEEKCON.class} , message = "周不能为空")
    private int week;

    /**
     * 用户名
     */
    @NotBlank(groups = {LendMixReimSelfShapeDTO.TESTWEEKCON.class,LendMixReimSelfShapeDTO.TESTMONCON.class,LendMixReimSelfShapeDTO.TESTYEARCON.class,LendMixReimSelfShapeDTO.TESTDAYCON.class} , message = "用户名不能为空")
    private String userName;

    public ReimburseShapeStatus getReimburseShapeStatus() {
        return reimburseShapeStatus;
    }

    public void setReimburseShapeStatus(ReimburseShapeStatus reimburseShapeStatus) {
        this.reimburseShapeStatus = reimburseShapeStatus;
    }

    public ReimShapeDetailTypeStatus getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(ReimShapeDetailTypeStatus typeStatus) {
        this.typeStatus = typeStatus;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}