package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 公司借入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 公司借入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostBorrowMoneyTO extends BaseTO {


    /**
     * 公司借入
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "公司借入名不能为空")
    private String costBorrowName;
    /**
     * 公司借入合计
     */
    private Double companyBorrowedSum;

    /**
     * 公司借入十日
     */
    private Double companyBorrowedTen;

    /**
     * 公司借入十五日
     */
    private Double companyBorrowedFift;

    /**
     * 公司借入二十日
     */
    private Double companyBorrowedTwtenty;

    /**
     * 公司借入三十日
     */
    private Double companyBorrowedThirty;

    public String getCostBorrowName() {
        return costBorrowName;
    }

    public void setCostBorrowName(String costBorrowName) {
        this.costBorrowName = costBorrowName;
    }

    public Double getCompanyBorrowedSum() {
        return companyBorrowedSum;
    }

    public void setCompanyBorrowedSum(Double companyBorrowedSum) {
        this.companyBorrowedSum = companyBorrowedSum;
    }

    public Double getCompanyBorrowedTen() {
        return companyBorrowedTen;
    }

    public void setCompanyBorrowedTen(Double companyBorrowedTen) {
        this.companyBorrowedTen = companyBorrowedTen;
    }

    public Double getCompanyBorrowedFift() {
        return companyBorrowedFift;
    }

    public void setCompanyBorrowedFift(Double companyBorrowedFift) {
        this.companyBorrowedFift = companyBorrowedFift;
    }

    public Double getCompanyBorrowedTwtenty() {
        return companyBorrowedTwtenty;
    }

    public void setCompanyBorrowedTwtenty(Double companyBorrowedTwtenty) {
        this.companyBorrowedTwtenty = companyBorrowedTwtenty;
    }

    public Double getCompanyBorrowedThirty() {
        return companyBorrowedThirty;
    }

    public void setCompanyBorrowedThirty(Double companyBorrowedThirty) {
        this.companyBorrowedThirty = companyBorrowedThirty;
    }
}