package com.bjike.goddess.reimbursementprepare.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * @Author: [yewenbo]
 * @Date: [2017-07-04 10:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportExcel extends BaseTO{
    /**
     * 时间
     */
    @ExcelHeader(name = "时间", notNull = true)
    private LocalDate lendDate;

    /**
     * 科目
     */
    @ExcelHeader(name = "科目", notNull = true)
    private String firstSubject;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String projectGroup;

    /**
     * 支付金额
     */
    @ExcelHeader(name = "支付金额", notNull = true)
    private Double lendMoney;

    /**
     * 是否付款
     */
    @ExcelHeader(name = "是否付款", notNull = true)
    private String payCondition;

    /**
     * 付款时间
     */
    @ExcelHeader(name = "付款时间", notNull = true)
    private LocalDate payDate;

    /**
     * 付款人
     */
    @ExcelHeader(name = "付款人", notNull = true)
    private String payer;

    /**
     * 付款状态
     */
    @ExcelHeader(name = "付款状态", notNull = true)
    private PayStatus payStatus;

    public LocalDate getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDate lendDate) {
        this.lendDate = lendDate;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(Double lendMoney) {
        this.lendMoney = lendMoney;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }
}
