package com.bjike.goddess.contractquotemanager.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 合同单价资料信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.309 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contractquotemanager_contractquotedata")
public class ContractQuoteData extends BaseEntity {

    /**
     * 客户名称
     */
    @Column(name = "customerName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '客户名称'")
    private String customerName;

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目'")
    private String project;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 适用年度开始时间
     */
    @Column(nullable = false, columnDefinition = "DATE COMMENT '适用年度开始时间'")
    private LocalDate suitableDateStart;

    /**
     * 适用年度结束时间
     */
    @Column(nullable = false, columnDefinition = "DATE COMMENT '适用年度结束时间'")
    private LocalDate suitableDateEnd;

    /**
     * 使用状态 0正常,1过期
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '使用状态'", nullable = false)
    private Status status;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getSuitableDateStart() {
        return suitableDateStart;
    }

    public void setSuitableDateStart(LocalDate suitableDateStart) {
        this.suitableDateStart = suitableDateStart;
    }

    public LocalDate getSuitableDateEnd() {
        return suitableDateEnd;
    }

    public void setSuitableDateEnd(LocalDate suitableDateEnd) {
        this.suitableDateEnd = suitableDateEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}