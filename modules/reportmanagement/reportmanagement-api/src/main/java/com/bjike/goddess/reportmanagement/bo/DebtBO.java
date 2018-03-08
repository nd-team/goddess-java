package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.reportmanagement.enums.DebtType;
import com.bjike.goddess.reportmanagement.enums.Type;

/**
 * 负债表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DebtBO extends BaseBO {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 负债和所有者权益(或股东权益)
     */
    private String debt;

    /**
     * 负债类型
     */
    private DebtType debtType;
    /**
     * 运算类型
     */
    private Type type;

    /**
     * 负债和所有者权益(或股东权益)行次
     */
    private Integer debtNum;

    /**
     * 负债年初数
     */
    private Double beginDebt;

    /**
     * 当前月发生额
     */
    private Double current;

    /**
     * 负债期末数
     */
    private Double endDebt;

    public DebtBO() {
    }

    public DebtBO(String startTime, String endTime, String debt, DebtType debtType, Type type, Integer debtNum, Double beginDebt, Double current, Double endDebt) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.debt = debt;
        this.debtType = debtType;
        this.type = type;
        this.debtNum = debtNum;
        this.beginDebt = beginDebt;
        this.current = current;
        this.endDebt = endDebt;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DebtType getDebtType() {
        return debtType;
    }

    public void setDebtType(DebtType debtType) {
        this.debtType = debtType;
    }

    public Integer getDebtNum() {
        return debtNum;
    }

    public void setDebtNum(Integer debtNum) {
        this.debtNum = debtNum;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Double getBeginDebt() {
        return beginDebt;
    }

    public void setBeginDebt(Double beginDebt) {
        this.beginDebt = beginDebt;
    }

    public Double getEndDebt() {
        return endDebt;
    }

    public void setEndDebt(Double endDebt) {
        this.endDebt = endDebt;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}