package com.bjike.goddess.contacts.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 外部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "contacts_external_contacts")
public class ExternalContacts extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 单位名称
     */
    @Column(name = "unit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '单位名称'")
    private String unit;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 联系电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255)   COMMENT '邮箱'")
    private String email;

    /**
     * 主要负责内容
     */
    @Column(name = "responsible", columnDefinition = "VARCHAR(255)   COMMENT '主要负责内容'")
    private String responsible;

    /**
     * 其他关联项目
     */
    @Column(name = "other", columnDefinition = "VARCHAR(255)   COMMENT '其他关联项目'")
    private String other;

    /**
     * 对外联系信息
     */
    @Column(name = "external", columnDefinition = "VARCHAR(255)   COMMENT '对外联系信息'")
    private String external;

    /**
     * 联系时间频率
     */
    @Column(name = "frequency", columnDefinition = "VARCHAR(255)   COMMENT '联系时间频率'")
    private String frequency;

    /**
     * 填写人
     */
    @Column(name = "writer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String writer;

    /**
     * 填写人编号
     */
    @Column(name = "writeNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String writeNumber;

    /**
     * 录入时间
     */
    @Column(name = "writeTime", nullable = false, columnDefinition = "DATETIME   COMMENT '录入时间'")
    private LocalDateTime writeTime;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWriteNumber() {
        return writeNumber;
    }

    public void setWriteNumber(String writeNumber) {
        this.writeNumber = writeNumber;
    }

    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}