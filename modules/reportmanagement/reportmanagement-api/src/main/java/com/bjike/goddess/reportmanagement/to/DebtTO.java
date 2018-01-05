package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.DebtType;
import com.bjike.goddess.reportmanagement.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 负债表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DebtTO extends BaseTO {

//    /**
//     * 起始时间
//     */
//    private String startTime;
//
//    /**
//     * 结束时间
//     */
//    private String endTime;

    /**
     * 负债和所有者权益(或股东权益)
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "负债和所有者权益(或股东权益)不能为空")
    private String debt;

//    /**
//     * 负债年初数
//     */
//    private Double beginDebt;
//
//    /**
//     * 负债期末数
//     */
//    private Double endAsset;

    /**
     * 负债类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "负债类型不能为空")
    private DebtType debtType;

    /**
     * 运算类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "运算类型不能为空")
    private Type type;

    public DebtType getDebtType() {
        return debtType;
    }

    public void setDebtType(DebtType debtType) {
        this.debtType = debtType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }
}