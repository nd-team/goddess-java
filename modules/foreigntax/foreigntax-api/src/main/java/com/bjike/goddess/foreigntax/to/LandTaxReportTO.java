package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 国地税报表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LandTaxReportTO extends BaseTO {

    /**
     * 公司
     */
    @NotBlank(message = "公司不能为空",groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 会计制度
     */
    @NotBlank(message = "会计制度不能为空",groups = {ADD.class, EDIT.class})
    private String accountSystem;

    /**
     * 报送所属期起
     */
    @NotBlank(message = "报送所属期起不能为空",groups = {ADD.class, EDIT.class})
    private String submitStartTime;

    /**
     * 报送所属期止
     */
    @NotBlank(message = "报送所属期止不能为空",groups = {ADD.class, EDIT.class})
    private String submitEndTime;

    /**
     * 报送状态
     */
    @NotBlank(message = "报送状态不能为空",groups = {ADD.class, EDIT.class})
    private String submitStatus;

    /**
     * 报送人员
     */
    @NotBlank(message = "报送人员不能为空",groups = {ADD.class, EDIT.class})
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