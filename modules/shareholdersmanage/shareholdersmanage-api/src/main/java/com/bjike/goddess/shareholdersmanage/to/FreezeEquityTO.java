package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 冻结股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FreezeEquityTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 股份数量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "股份数量不能为空")
    private Integer totalHoldNum;

    /**
     * 金额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "金额不能为空")
    private Double amount;

    /**
     * 冻结日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "冻结日期不能为空")
    private String freezeDate;

    /**
     * 冻结原因
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "冻结原因不能为空")
    private String freezeWhy;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTotalHoldNum() {
        return totalHoldNum;
    }

    public void setTotalHoldNum(Integer totalHoldNum) {
        this.totalHoldNum = totalHoldNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(String freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getFreezeWhy() {
        return freezeWhy;
    }

    public void setFreezeWhy(String freezeWhy) {
        this.freezeWhy = freezeWhy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}