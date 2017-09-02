package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 注销股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:51 ]
 * @Description: [ 注销股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LogoutEquityTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区姓名不能为空")
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
     * 注销股权数/万
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "注销股权数不能为空")
    private Integer logoutHoldNum;

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
     * 注销日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "注销日期不能为空")
    private String logoutDate;

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

    public Integer getLogoutHoldNum() {
        return logoutHoldNum;
    }

    public void setLogoutHoldNum(Integer logoutHoldNum) {
        this.logoutHoldNum = logoutHoldNum;
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

    public String getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}