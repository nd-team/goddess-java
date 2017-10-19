package com.bjike.goddess.foreigntax.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 国地税报表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LandTaxReportBO extends BaseBO {

    /**
     * 公司
     */
    private String company;

    /**
     * 会计制度
     */
    private String accountSystem;

    /**
     * 报送所属期起
     */
    private String submitStartTime;

    /**
     * 报送所属期止
     */
    private String submitEndTime;

    /**
     * 报送状态
     */
    private String submitStatus;

    /**
     * 报送人员
     */
    private String submitStaff;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAccountSystem() {
        return accountSystem;
    }

    public void setAccountSystem(String accountSystem) {
        this.accountSystem = accountSystem;
    }

    public String getSubmitStartTime() {
        return submitStartTime;
    }

    public void setSubmitStartTime(String submitStartTime) {
        this.submitStartTime = submitStartTime;
    }

    public String getSubmitEndTime() {
        return submitEndTime;
    }

    public void setSubmitEndTime(String submitEndTime) {
        this.submitEndTime = submitEndTime;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getSubmitStaff() {
        return submitStaff;
    }

    public void setSubmitStaff(String submitStaff) {
        this.submitStaff = submitStaff;
    }
}