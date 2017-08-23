package com.bjike.goddess.reportmanagement.vo;

import com.bjike.goddess.reportmanagement.enums.DebtType;
import com.bjike.goddess.reportmanagement.enums.Type;

/**
 * 负债表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DebtVO {

    /**
     * id
     */
    private String debtId;

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

    public DebtType getDebtType() {
        return debtType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDebtType(DebtType debtType) {
        this.debtType = debtType;
    }

    public String getDebtId() {
        return debtId;
    }

    public void setDebtId(String debtId) {
        this.debtId = debtId;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Integer getDebtNum() {
        return debtNum;
    }

    public void setDebtNum(Integer debtNum) {
        this.debtNum = debtNum;
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
}