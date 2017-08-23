package com.bjike.goddess.costdetail.vo;

/**
 * 公司借入明细表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:11 ]
 * @Description: [ 公司借入明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyBorrowedDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 分类名
     */
    private String typeName;

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

    /**
     * 成本明细表id
     */
    private String costId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}