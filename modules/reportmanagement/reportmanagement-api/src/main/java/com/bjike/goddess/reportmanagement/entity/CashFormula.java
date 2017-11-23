package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目公式
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-21 05:09 ]
 * @Description: [ 项目公式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_cashformula")
public class CashFormula extends BaseEntity {

    /**
     * 项目id
     */
    @Column(name = "projectId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目id'")
    private String projectId;

    /**
     * 项目
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String projectName;

    /**
     * 公式
     */
    @Column(name = "form", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '公式'")
    private String form;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}