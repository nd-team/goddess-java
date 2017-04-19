package com.bjike.goddess.business.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 工商年检信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessAnnualInfoTO extends BaseTO {

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