package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.DebtType;
import com.bjike.goddess.reportmanagement.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 负债表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_debt")
public class Debt extends BaseEntity {

//    /**
//     * 起始时间
//     */
//    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '起始时间'")
//    private LocalDate startTime;
//
//    /**
//     * 结束时间
//     */
//    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '结束时间'")
//    private LocalDate endTime;

    /**
     * 负债和所有者权益(或股东权益)
     */
    @Column(name = "debt", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '负债和所有者权益(或股东权益)'")
    private String debt;

    /**
     * 负债类型
     */
    @Column(name = "debtType", columnDefinition = "TINYINT(2)   COMMENT '负债类型'")
    private DebtType debtType;

    /**
     * 运算类型
     */
    @Column(name = "type", columnDefinition = "TINYINT(2)   COMMENT '运算类型'")
    private Type type;

//    /**
//     * 负债年初数
//     */
//    @Column(name = "beginDebt", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '负债年初数'")
//    private Double beginDebt;
//
//    /**
//     * 负债期末数
//     */
//    @Column(name = "endAsset", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '负债期末数'")
//    private Double endAsset;


//    public LocalDate getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDate startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDate getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDate endTime) {
//        this.endTime = endTime;
//    }


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

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "Debt{" +
                "debt='" + debt + '\'' +
                ", debtType=" + debtType +
                ", type=" + type +
                '}';
    }
}