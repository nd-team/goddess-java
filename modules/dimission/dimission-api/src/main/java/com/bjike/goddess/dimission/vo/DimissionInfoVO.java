package com.bjike.goddess.dimission.vo;

import com.bjike.goddess.dimission.enums.*;

/**
 * 离职信息表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DimissionInfoVO {

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String department;

    /**
     * 员工编号
     */
    private String employeeNumber;

    /**
     * 岗位
     */
    private String position;

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 学历
     */
    private String education;

    /**
     * 在司工龄
     */
    private String seniority;

    /**
     * 私人邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 离职类型
     */
    private DimissionType type;

    /**
     * 离职原因
     */
    private String reason;

    /**
     * 员工状态
     */
    private EmployeeStatus status;

    /**
     * 申请离职日期
     */
    private String applyDate;

    /**
     * 是否申请提前离职
     */
    private Boolean advanceApply;

    /**
     * 提前离职日期
     */
    private String advanceDate;

    /**
     * 提前离职原因
     */
    private String advanceReason;

    /**
     * 离职状态
     */
    private DimissionStatus dimission;

    /**
     * 正常离职时间
     */
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
     * 项目经理
     */
    private String manage;

    /**
     * 项目经理面谈意见
     */
    private String manageOpinion;

    /**
     * 总经办审批意见
     */
    private String generalOpinion;

    /**
     * 通过提前离职
     */
    private Boolean advance;

    /**
     * 离职办理状态
     */
    private HandleStatus handle;

    /**
     * 薪资确认
     */
    private ConfirmationType salaryConfirmation;

    /**
     * 离职确认情况
     */
    private ConfirmationType dimissionConfirmation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public DimissionType getType() {
        return type;
    }

    public void setType(DimissionType type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Boolean getAdvanceApply() {
        return advanceApply;
    }

    public void setAdvanceApply(Boolean advanceApply) {
        this.advanceApply = advanceApply;
    }

    public String getAdvanceDate() {
        return advanceDate;
    }

    public void setAdvanceDate(String advanceDate) {
        this.advanceDate = advanceDate;
    }

    public String getAdvanceReason() {
        return advanceReason;
    }

    public void setAdvanceReason(String advanceReason) {
        this.advanceReason = advanceReason;
    }

    public DimissionStatus getDimission() {
        return dimission;
    }

    public void setDimission(DimissionStatus dimission) {
        this.dimission = dimission;
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

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getManageOpinion() {
        return manageOpinion;
    }

    public void setManageOpinion(String manageOpinion) {
        this.manageOpinion = manageOpinion;
    }

    public String getGeneralOpinion() {
        return generalOpinion;
    }

    public void setGeneralOpinion(String generalOpinion) {
        this.generalOpinion = generalOpinion;
    }

    public Boolean getAdvance() {
        return advance;
    }

    public void setAdvance(Boolean advance) {
        this.advance = advance;
    }

    public HandleStatus getHandle() {
        return handle;
    }

    public void setHandle(HandleStatus handle) {
        this.handle = handle;
    }

    public ConfirmationType getSalaryConfirmation() {
        return salaryConfirmation;
    }

    public void setSalaryConfirmation(ConfirmationType salaryConfirmation) {
        this.salaryConfirmation = salaryConfirmation;
    }

    public ConfirmationType getDimissionConfirmation() {
        return dimissionConfirmation;
    }

    public void setDimissionConfirmation(ConfirmationType dimissionConfirmation) {
        this.dimissionConfirmation = dimissionConfirmation;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}