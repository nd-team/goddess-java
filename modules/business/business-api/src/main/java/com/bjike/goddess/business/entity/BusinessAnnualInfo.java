package com.bjike.goddess.business.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 工商年检信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "business_businessannualinfo")
public class BusinessAnnualInfo extends BaseEntity {

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 报告年份
     */
    @Column(name = "reportYear", nullable = false, columnDefinition = "DATE   COMMENT '报告年份'")
    private LocalDate reportYear;

    /**
     * 提交日期
     */
    @Column(name = "submitDate", nullable = false, columnDefinition = "DATE   COMMENT '提交日期'")
    private LocalDate submitDate;

    /**
     * 状态(是否公示)
     */
    @Column(name = "is_status", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '状态(是否公示)'", insertable = false)
    private Boolean status;

    /**
     * 年报
     */
    @Column(name = "annualReport", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年报'")
    private String annualReport;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getReportYear() {
        return reportYear;
    }

    public void setReportYear(LocalDate reportYear) {
        this.reportYear = reportYear;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAnnualReport() {
        return annualReport;
    }

    public void setAnnualReport(String annualReport) {
        this.annualReport = annualReport;
    }
}