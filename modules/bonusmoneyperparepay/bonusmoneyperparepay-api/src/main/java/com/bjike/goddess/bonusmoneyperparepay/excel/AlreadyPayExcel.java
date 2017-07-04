package com.bjike.goddess.bonusmoneyperparepay.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 已付款
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 已付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

public class AlreadyPayExcel extends BaseTO {

    /**
     * 时间
     */
    @ExcelHeader(name = "时间", notNull = true)
    private String yearsMonth;

    /**
     * 支付状态
     */
    @ExcelHeader(name = "支付状态", notNull = true)
    private String payStatus;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String projectGroup;

    /**
     * 科目
     */
    @ExcelHeader(name = "科目", notNull = true)
    private String subjects;

    /**
     * 准备金
     */
    @ExcelHeader(name = "准备金", notNull = true)
    private Double reserve;


    /**
     * 是否付款
     */
    @ExcelHeader(name = "是否付款", notNull = true)
    private String turntable;

    /**
     * 支付金额
     */
    @ExcelHeader(name = "支付金额", notNull = true)
    private Double payMoney;

    /**
     * 付款时间
     */
    @ExcelHeader(name = "付款时间", notNull = true)
    private String difference;

    /**
     * 付款人
     */
    @ExcelHeader(name = "付款人", notNull = true)
    private String payAuthor;


    public String getYearsMonth() {
        return yearsMonth;
    }

    public void setYearsMonth(String yearsMonth) {
        this.yearsMonth = yearsMonth;
    }

    public String getTurntable() {
        return turntable;
    }

    public void setTurntable(String turntable) {
        this.turntable = turntable;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getPayAuthor() {
        return payAuthor;
    }

    public void setPayAuthor(String payAuthor) {
        this.payAuthor = payAuthor;
    }
}