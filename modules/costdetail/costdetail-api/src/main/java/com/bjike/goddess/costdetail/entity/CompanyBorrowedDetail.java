package com.bjike.goddess.costdetail.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 公司借入明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:11 ]
 * @Description: [ 公司借入明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "costdetail_companyborroweddetail")
public class CompanyBorrowedDetail extends BaseEntity {

    /**
     * 日期
     */
    @Column(name = "costTime", nullable = false, columnDefinition = "DATE   COMMENT '日期'")
    private LocalDate costTime;

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;
    /**
     * 分类名
     */
    @Column(name = "typeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类名'")
    private String typeName;

    /**
     * 公司借入合计
     */
    @Column(name = "companyBorrowedSum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司借入合计'")
    private Double companyBorrowedSum;

    /**
     * 公司借入十日
     */
    @Column(name = "companyBorrowedTen", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司借入十日'")
    private Double companyBorrowedTen;

    /**
     * 公司借入十五日
     */
    @Column(name = "companyBorrowedFift", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司借入十五日'")
    private Double companyBorrowedFift;

    /**
     * 公司借入二十日
     */
    @Column(name = "companyBorrowedTwtenty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司借入二十日'")
    private Double companyBorrowedTwtenty;

    /**
     * 公司借入三十日
     */
    @Column(name = "companyBorrowedThirty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司借入三十日'")
    private Double companyBorrowedThirty;

    /**
     * 成本明细表id
     */
    @Column(name = "costId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '成本明细表id'")
    private String costId;


    public LocalDate getCostTime() {
        return costTime;
    }

    public void setCostTime(LocalDate costTime) {
        this.costTime = costTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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