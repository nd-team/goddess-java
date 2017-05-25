package com.bjike.goddess.dimission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.dimission.enums.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 离职信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dimission_info")
public class DimissionInfo extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 私人邮箱
     */
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '私人邮箱'")
    private String email;

    /**
     * 离职类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '离职类型'")
    private DimissionType type;

    /**
     * 离职原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '离职原因'")
    private String reason;

    /**
     * 员工状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '员工状态'")
    private EmployeeStatus status;

    /**
     * 申请离职日期
     */
    @Column(name = "applyDate", columnDefinition = "DATE   COMMENT '申请离职日期'")
    private LocalDate applyDate;

    /**
     * 是否申请提前离职
     */
    @Column(name = "is_advanceApply", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否申请提前离职'")
    private Boolean advanceApply;

    /**
     * 提前离职日期
     */
    @Column(name = "advanceDate", columnDefinition = "DATE   COMMENT '提前离职日期'")
    private LocalDate advanceDate;

    /**
     * 提前离职原因
     */
    @Column(name = "advanceReason", columnDefinition = "VARCHAR(255)   COMMENT '提前离职原因'")
    private String advanceReason;

    /**
     * 离职状态
     */
    @Column(name = "dimission", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '离职状态'")
    private DimissionStatus dimission;

    /**
     * 正常离职时间
     */
    @Column(name = "dimissionDate", columnDefinition = "DATE   COMMENT '正常离职时间'")
    private LocalDate dimissionDate;

    /**
     * 离职面谈负责人
     */
    @Column(name = "liable", columnDefinition = "VARCHAR(255)   COMMENT '离职面谈负责人'")
    private String liable;

    /**
     * 面谈内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '面谈内容'")
    private String content;

    /**
     * 面谈意见
     */
    @Column(name = "liableOpinion", columnDefinition = "VARCHAR(255)   COMMENT '面谈意见'")
    private String liableOpinion;

    /**
     * 项目经理
     */
    @Column(name = "manage", columnDefinition = "VARCHAR(255)   COMMENT '项目经理'")
    private String manage;

    /**
     * 项目经理面谈意见
     */
    @Column(name = "manageOpinion", columnDefinition = "VARCHAR(255)   COMMENT '项目经理面谈意见'")
    private String manageOpinion;

    /**
     * 总经办审批意见
     */
    @Column(name = "generalOpinion", columnDefinition = "VARCHAR(255)   COMMENT '总经办审批意见'")
    private String generalOpinion;

    /**
     * 通过提前离职
     */
    @Column(name = "is_advance", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '通过提前离职'", insertable = false)
    private Boolean advance;

    /**
     * 离职办理状态
     */
    @Column(name = "handle", columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '离职办理状态'")
    private HandleStatus handle;

    /**
     * 薪资确认
     */
    @Column(name = "salaryConfirmation", columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '薪资确认'")
    private ConfirmationType salaryConfirmation;

    /**
     * 离职确认情况
     */
    @Column(name = "dimissionConfirmation", columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '离职确认情况'")
    private ConfirmationType dimissionConfirmation;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public Boolean getAdvanceApply() {
        return advanceApply;
    }

    public void setAdvanceApply(Boolean advanceApply) {
        this.advanceApply = advanceApply;
    }

    public LocalDate getAdvanceDate() {
        return advanceDate;
    }

    public void setAdvanceDate(LocalDate advanceDate) {
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

    public LocalDate getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(LocalDate dimissionDate) {
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
}