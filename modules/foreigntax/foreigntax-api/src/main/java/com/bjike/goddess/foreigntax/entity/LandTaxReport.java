package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 国地税报表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_landtaxreport")
public class LandTaxReport extends BaseEntity {

    /**
     * 公司
     */
    @Column(name = "company", columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;

    /**
     * 会计制度
     */
    @Column(name = "accountSystem", columnDefinition = "VARCHAR(255)   COMMENT '会计制度'")
    private String accountSystem;

    /**
     * 报送所属期起
     */
    @Column(name = "submitStartTime", columnDefinition = "DATE   COMMENT '报送所属期起'")
    private String submitStartTime;

    /**
     * 报送所属期止
     */
    @Column(name = "submitEndTime", columnDefinition = "DATE   COMMENT '报送所属期止'")
    private String submitEndTime;

    /**
     * 报送状态
     */
    @Column(name = "submitStatus", columnDefinition = "VARCHAR(255)   COMMENT '报送状态'")
    private String submitStatus;

    /**
     * 报送人员
     */
    @Column(name = "submitStaff", columnDefinition = "VARCHAR(255)   COMMENT '报送人员'")
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