package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 业务提成分配比例表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_proportion")
public class Proportion extends BaseEntity {

    /**
     * 业务提成分配比例协商时间
     */
    @Column(name = "time1", nullable = false, columnDefinition = "DATETIME   COMMENT '业务提成分配比例协商时间'")
    private LocalDateTime time;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 部门/项目组
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'")
    private String department;

    /**
     * 内部项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String projectName;

    /**
     * 参与协商人
     */
    @Column(name = "consultants", columnDefinition = "VARCHAR(255)   COMMENT '参与协商人'")
    private String consultants;

    /**
     * 提成分配比例确认单是否全部确认
     */
    @Column(name = "is_confirm",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '提成分配比例确认单是否全部确认'", insertable = false)
    private Boolean confirm;

    /**
     * 已确认人
     */
    @Column(name = "confirmed",  columnDefinition = "VARCHAR(255)   COMMENT '已确认人'")
    private String confirmed;

    /**
     * 未确认人
     */
    @Column(name = "notConfirmed",  columnDefinition = "VARCHAR(255)   COMMENT '未确认人'")
    private String notConfirmed;


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getNotConfirmed() {
        return notConfirmed;
    }

    public void setNotConfirmed(String notConfirmed) {
        this.notConfirmed = notConfirmed;
    }
}