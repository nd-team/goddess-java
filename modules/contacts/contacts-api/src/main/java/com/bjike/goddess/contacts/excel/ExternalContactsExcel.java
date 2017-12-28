package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

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
public class ExternalContactsExcel extends BaseEntity {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区" , notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组" , notNull = false)
    private String project;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名" , notNull = true)
    private String username;

    /**
     * 单位名称
     */
    @ExcelHeader(name = "单位名称" , notNull = true)
    private String unit;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位" , notNull = true)
    private String position;

    /**
     * 联系电话
     */
    @ExcelHeader(name = "联系电话" , notNull = true)
    private String phone;

    /**
     * 邮箱
     */
    @ExcelHeader(name = "邮箱" , notNull = false)
    private String email;

    /**
     * 主要负责内容
     */
    @ExcelHeader(name = "主要负责内容" , notNull = false)
    private String responsible;

    /**
     * 其他关联项目
     */
    @ExcelHeader(name = "其他关联项目" , notNull = false)
    private String other;

    /**
     * 对外联系信息
     */
    @ExcelHeader(name = "对外联系信息" , notNull = false)
    private String external;

    /**
     * 联系时间频率
     */
    @ExcelHeader(name = "联系时间频率" , notNull = false)
    private String frequency;

    /**
     * 填写人
     */
    @ExcelHeader(name = "填写人" , notNull = true)
    private String writer;

    /**
     * 填写人编号
     */
    @ExcelHeader(name = "填写人编号" , notNull = true)
    private String writeNumber;

    /**
     * 录入时间
     */
    @ExcelHeader(name = "录入时间" , notNull = true)
    private LocalDateTime writeTime;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注" , notNull = false)
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