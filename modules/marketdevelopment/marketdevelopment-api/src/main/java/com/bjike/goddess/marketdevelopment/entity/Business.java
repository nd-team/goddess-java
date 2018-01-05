package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-28 02:54 ]
 * @Description: [ 业务对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_business")
public class Business extends BaseEntity {

    /**
     * 业务对象编号
     */
    @Column(name = "businessNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务对象编号'")
    private String businessNum;

    /**
     * 业务对象类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务对象类型'")
    private String businessType;

    /**
     * 公司名称编号
     */
    @Column(name = "companyNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称编号'")
    private String companyNum;

    /**
     * 业务对象-公司名称
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务对象-公司名称'")
    private String company;

    /**
     * 可发展业务方向-科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '可发展业务方向-科目'")
    private String subject;

    /**
     * 可发展业务-科目合计
     */
    @Column(name = "sum", nullable = false, columnDefinition = "INTEGER   COMMENT '可发展业务-科目合计'")
    private Integer sum;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态' ", nullable = false,insertable = false)
    private Status status;


    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}