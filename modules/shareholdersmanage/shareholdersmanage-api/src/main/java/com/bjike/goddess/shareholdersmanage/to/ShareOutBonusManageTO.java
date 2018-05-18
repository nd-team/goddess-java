package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 分红管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusManageTO extends BaseTO {

    /**
     * 分红日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分红日期不能为空")
    private String shareOutBonusDate;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 分红名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分红名称不能为空")
    private String shareOutBonusName;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 共派股
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "共派股不能为空")
    private Integer totalSentStocks;
    /**
     * 每股分红
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "每股分红不能为空")
    private Double perShareDividends;
    /**
     * 备注
     */
    private String remark;


    public String getShareOutBonusDate() {
        return shareOutBonusDate;
    }

    public void setShareOutBonusDate(String shareOutBonusDate) {
        this.shareOutBonusDate = shareOutBonusDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareOutBonusName() {
        return shareOutBonusName;
    }

    public void setShareOutBonusName(String shareOutBonusName) {
        this.shareOutBonusName = shareOutBonusName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTotalSentStocks() {
        return totalSentStocks;
    }

    public void setTotalSentStocks(Integer totalSentStocks) {
        this.totalSentStocks = totalSentStocks;
    }

    public Double getPerShareDividends() {
        return perShareDividends;
    }

    public void setPerShareDividends(Double perShareDividends) {
        this.perShareDividends = perShareDividends;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}