package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 股权合并
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityCoalescTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 合并方
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "合并方不能为空")
    private String combined;

    /**
     * 被合并方
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "被合并方不能为空")
    private String beCombined;

    /**
     * 合并方式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "合并方式不能为空")
    private String coalescWay;

    /**
     * 合并日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "合并日期不能为空")
    private String coalescDate;

    /**
     * 占股比例
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "占股比例不能为空")
    private Double percentage;

    /**
     * 金额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "金额不能为空")
    private Double amount;

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

    public String getCombined() {
        return combined;
    }

    public void setCombined(String combined) {
        this.combined = combined;
    }

    public String getBeCombined() {
        return beCombined;
    }

    public void setBeCombined(String beCombined) {
        this.beCombined = beCombined;
    }

    public String getCoalescWay() {
        return coalescWay;
    }

    public void setCoalescWay(String coalescWay) {
        this.coalescWay = coalescWay;
    }

    public String getCoalescDate() {
        return coalescDate;
    }

    public void setCoalescDate(String coalescDate) {
        this.coalescDate = coalescDate;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}