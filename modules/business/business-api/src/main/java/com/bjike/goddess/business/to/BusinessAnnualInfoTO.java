package com.bjike.goddess.business.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 报告年份
     */
    @NotBlank(message = "报告年份不能为空",groups = {ADD.class, EDIT.class})
    private String reportYear;

    /**
     * 提交日期
     */
    @NotBlank(message = "提交日期不能为空",groups = {ADD.class, EDIT.class})
    private String submitDate;

    /**
     * 状态(是否公示)
     */
    @NotNull(message = "状态(是否公示)不能为空",groups = {ADD.class, EDIT.class})
    private Boolean status;

    /**
     * 年报
     */
    @NotBlank(message = "年报不能为空",groups = {ADD.class, EDIT.class})
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