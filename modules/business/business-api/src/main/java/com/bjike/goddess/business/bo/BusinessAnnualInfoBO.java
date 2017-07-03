package com.bjike.goddess.business.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 工商年检信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessAnnualInfoBO extends BaseBO {

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 报告年份
     */
    private String reportYear;

    /**
     * 提交日期
     */
    private String submitDate;

    /**
     * 状态(是否公示)
     */
    private Boolean status;

    /**
     * 年报
     */
    private String annualReport;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReportYear() {
        return reportYear;
    }

    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
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