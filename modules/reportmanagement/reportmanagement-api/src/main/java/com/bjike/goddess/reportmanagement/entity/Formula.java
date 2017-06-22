package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.Form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 对应的公式
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_formula")
public class Formula extends BaseEntity {

    /**
     * 科目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String project;

    /**
     * 类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 公式添加的形式
     */
    @Column(name = "form", columnDefinition = "TINYINT(2)   COMMENT '公式添加的形式'")
    private Form form;

    /**
     * 对应科目的id
     */
    @Column(name = "foreign_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '对应科目的id'")
    private String foreignId;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}