package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dimission.enums.*;
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
public class DimissionInfoTO extends BaseTO {

    /**
     * 姓名
     */
    private String username;

    /**
     * 私人邮箱
     */
    @NotBlank(message = "私人邮箱不能为空", groups = {ADD.class, EDIT.class})
    private String email;

    /**
     * 离职类型
     */
    private DimissionType type;

    /**
     * 离职原因
     */
    @NotBlank(message = "离职原因不能为空", groups = {ADD.class, EDIT.class})
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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Boolean getAdvance() {
        return advance;
    }

    public void setAdvance(Boolean advance) {
        this.advance = advance;
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

    public Boolean getAdvanceApply() {
        return advanceApply;
    }

    public void setAdvanceApply(Boolean advanceApply) {
        this.advanceApply = advanceApply;
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