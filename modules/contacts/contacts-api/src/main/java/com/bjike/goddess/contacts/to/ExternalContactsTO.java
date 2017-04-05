package com.bjike.goddess.contacts.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 外部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExternalContactsTO extends BaseTO {

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    private String project;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 单位名称
     */
    @NotNull(message = "单位名称不能为空", groups = {ADD.class, EDIT.class})
    private String unit;

    /**
     * 岗位
     */
    @NotNull(message = "岗位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 联系电话
     */
    @NotNull(message = "联系电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 主要负责内容
     */
    private String responsible;

    /**
     * 其他关联项目
     */
    private String other;

    /**
     * 对外联系信息
     */
    private String external;

    /**
     * 联系时间频率
     */
    private String frequency;

    /**
     * 填写人
     */
    private String writer;

    /**
     * 填写人编号
     */
    private String writeNumber;

    /**
     * 录入时间
     */
    private String writeTime;

    /**
     * 备注
     */
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

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}