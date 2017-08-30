package com.bjike.goddess.reimbursementprepare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reimbursementprepare_waitpay")
public class WaitPay extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "lendDate", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate lendDate;

    /**
     * 科目
     */
    @Column(name = "firstSubject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String firstSubject;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 支付金额
     */
    @Column(name = "lendMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '支付金额'")
    private Double lendMoney;

    /**
     * 是否付款
     */
    @Column(name = "payCondition", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '是否付款'")
    private String payCondition;

    /**
     * 付款时间
     */
    @Column(name = "payDate", nullable = false, columnDefinition = "DATE   COMMENT '付款时间'")
    private LocalDate payDate;

    /**
     * 付款人
     */
    @Column(name = "payer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '付款人'")
    private String payer;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 付款状态
     */
    @Column(name = "payStatus", nullable = false, columnDefinition = "TINYINT(2)    COMMENT '付款状态'")
    private PayStatus payStatus;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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