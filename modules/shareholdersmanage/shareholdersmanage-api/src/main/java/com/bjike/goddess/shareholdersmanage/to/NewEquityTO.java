package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 新增股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NewEquityTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 股东姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股东姓名不能为空")
    private String shareholderName;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 新增股数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "新增股数不能为空")
    private Integer newHoldNum;

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
     * 新增时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "新增时间不能为空")
    private String newSaveDate;

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

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getNewHoldNum() {
        return newHoldNum;
    }

    public void setNewHoldNum(Integer newHoldNum) {
        this.newHoldNum = newHoldNum;
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

    public String getNewSaveDate() {
        return newSaveDate;
    }

    public void setNewSaveDate(String newSaveDate) {
        this.newSaveDate = newSaveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}