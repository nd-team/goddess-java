package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 公司借出明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:18 ]
 * @Description: [ 公司借出明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyLendDetailTO extends BaseTO {

    /**
     * 分类名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分类名")
    private String typeName;

    /**
     * 公司借出合计
     */
    private Double companyLendSum;

    /**
     * 公司借出十日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "公司借出十日")
    private Double companyLendTen;

    /**
     * 公司借出十五日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "公司借出十五日")
    private Double companyLendFift;

    /**
     * 公司借出二十日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "公司借出二十日")
    private Double companyLendTwtenty;

    /**
     * 公司借出三十日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "公司借出三十日")
    private Double companyLendThirty;

    /**
     * 成本明细表id
     */
    private String costId;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getCompanyLendSum() {
        return companyLendSum;
    }

    public void setCompanyLendSum(Double companyLendSum) {
        this.companyLendSum = companyLendSum;
    }

    public Double getCompanyLendTen() {
        return companyLendTen;
    }

    public void setCompanyLendTen(Double companyLendTen) {
        this.companyLendTen = companyLendTen;
    }

    public Double getCompanyLendFift() {
        return companyLendFift;
    }

    public void setCompanyLendFift(Double companyLendFift) {
        this.companyLendFift = companyLendFift;
    }

    public Double getCompanyLendTwtenty() {
        return companyLendTwtenty;
    }

    public void setCompanyLendTwtenty(Double companyLendTwtenty) {
        this.companyLendTwtenty = companyLendTwtenty;
    }

    public Double getCompanyLendThirty() {
        return companyLendThirty;
    }

    public void setCompanyLendThirty(Double companyLendThirty) {
        this.companyLendThirty = companyLendThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}