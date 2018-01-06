package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dimission.enums.ConfirmationType;
import com.bjike.goddess.dimission.enums.HandleStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 离职信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FromInfoTO extends BaseTO {

    /**
     * 自离人员姓名
     */
    @NotBlank(message = "离职人员姓名", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String employeeNumber;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 岗位层级
     */
    @NotBlank(message = "岗位层级不能为空", groups = {ADD.class, EDIT.class})
    private String arrangement;

    /**
     * 学历
     */
    @NotBlank(message = "学历不能为空", groups = {ADD.class, EDIT.class})
    private String education;

    /**
     * 在司工龄(月)
     */
    @NotBlank(message = "在司工龄(月)不能为空", groups = {ADD.class, EDIT.class})
    private String seniority;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空", groups = {ADD.class, EDIT.class})
    private String entryTime;


    /**
     * 私人邮箱
     */
    @NotBlank(message = "私人邮箱姓名", groups = {ADD.class, EDIT.class})
    private String email;
    /**
     * 离职原因
     */
    @NotBlank(message = "离职原因不能为空", groups = {ADD.class, EDIT.class})
    private String reason;

    /**
     * 申请离职日期
     */
    private String applyDate;

    /**
     * 正常离职时间
     */
    @NotBlank(message = "正常离职时间不能为空", groups = {ADD.class, EDIT.class})
    private String dimissionDate;

    /**
     * 离职面谈负责人
     */
    private String liable;


    /**
     * 面谈内容
     */
    private String content;

    /**
     * 面谈意见
     */
    private String liableOpinion;

    /**
     * 离职办理状态
     */
    @NotNull(message = "离职办理状态不能为空", groups = {ADD.class, EDIT.class})
    private HandleStatus handle;

    /**
     * 离职确认情况
     */
    @NotNull(message = "离职确认情况不能为空", groups = {ADD.class, EDIT.class})
    private ConfirmationType dimissionConfirmation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(String dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public String getLiable() {
        return liable;
    }

    public void setLiable(String liable) {
        this.liable = liable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLiableOpinion() {
        return liableOpinion;
    }

    public void setLiableOpinion(String liableOpinion) {
        this.liableOpinion = liableOpinion;
    }

    public HandleStatus getHandle() {
        return handle;
    }

    public void setHandle(HandleStatus handle) {
        this.handle = handle;
    }

    public ConfirmationType getDimissionConfirmation() {
        return dimissionConfirmation;
    }

    public void setDimissionConfirmation(ConfirmationType dimissionConfirmation) {
        this.dimissionConfirmation = dimissionConfirmation;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}