package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 股权转让
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:32 ]
 * @Description: [ 股权转让 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityTransferTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 转让人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "转让人不能为空")
    private String assignor;

    /**
     * 受让人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "受让人不能为空")
    private String receiving;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 转让股数/万
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "转让股数不能为空")
    private Integer transferHoldNum;

    /**
     * 每股价格/元
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "每股价格不能为空")
    private Double perSharePrice;

    /**
     * 金额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "金额不能为空")
    private Double amount;

    /**
     * 转让时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "转让时间不能为空")
    private String transferDate;

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

    public String getAssignor() {
        return assignor;
    }

    public void setAssignor(String assignor) {
        this.assignor = assignor;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTransferHoldNum() {
        return transferHoldNum;
    }

    public void setTransferHoldNum(Integer transferHoldNum) {
        this.transferHoldNum = transferHoldNum;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}