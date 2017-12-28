package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.recruit.type.Gender;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class IdeaTO extends BaseTO {
    public interface FirstIdea{}
    public interface SecondIdea{}
    public interface WagesIdea{}
    public interface BossIdea{}
    public interface StaffEntry{}
    /**
     * 初试意见
     */
    @NotBlank(message = "初试意见不能为空",groups = {IdeaTO.FirstIdea.class})
    private String firstTestAdvice;

    /**
     * 初试是否通过
     */
    @NotNull(message = "初试是否通过不能为空",groups = {IdeaTO.FirstIdea.class})
    private Boolean whetherFirstTestPass;

    /**
     * 复试意见
     */
    @NotBlank(message = "复试意见不能为空",groups = {IdeaTO.SecondIdea.class})
    private String secondTestAdvice;

    /**
     * 复试是否通过
     */
    @NotNull(message = "复试是否通过不能为空",groups = {IdeaTO.SecondIdea.class})
    private Boolean whetherSecondTestPass;

    /**
     * 薪资面谈时间
     */
    @NotBlank(message = "薪资面谈时间不能为空",groups = {IdeaTO.WagesIdea.class})
    private String salaryFaceTime;

    /**
     * 薪资面谈负责人
     */
    @NotBlank(message = "薪资面谈负责人不能为空",groups = {IdeaTO.WagesIdea.class})
    private String salaryFacePrincipal;
    /**
     * 面谈意见
     */
    @NotBlank(message = "面谈意见不能为空",groups = {IdeaTO.WagesIdea.class})
    private String faceAdvice;
    /**
     * 总经办
     */
    @NotBlank(message = "总经办不能为空",groups = {IdeaTO.BossIdea.class})
    private String boss;

    /**
     * 总经办意见
     */
    @NotBlank(message = "总经办意见不能为空",groups = {IdeaTO.BossIdea.class})
    private String bossAdvice;
    /**
     * 是否同意录用
     */
    @NotNull(message = "是否同意录用不能为空",groups = {IdeaTO.BossIdea.class})
    private Boolean agreedEmployed;

    /**
     * 审批时间
     */
    @NotBlank(message = "审批时间不能为空",groups = {IdeaTO.BossIdea.class})
    private String auditTime;

    /**
     * 是否接受录取
     */
    @NotNull(message = "是否接受录取不能为空",groups = {IdeaTO.StaffEntry.class})
    private Boolean whetherAcceptAdmit;
    /**
     * 未接受录取原因
     */
    @NotBlank(message = "未接受录取原因不能为空",groups = {IdeaTO.StaffEntry.class})
    private String denyAdmitReason;
    /**
     * 入职时间
     */
    @NotBlank(message = "入职时间不能为空",groups = {IdeaTO.StaffEntry.class})
    private String entryTime;

    /**
     * 是否住宿
     */
    @NotNull(message = "是否住宿不能为空",groups = {IdeaTO.StaffEntry.class})
    private Boolean whetherAccommodation;

    /**
     * 是否使用公司电脑
     */
    @NotNull(message = "是否使用公司电脑不能为空",groups = {IdeaTO.StaffEntry.class})
    private Boolean whetherUseFirmPC;
    /**
     * 入职地址
     */
    @NotBlank(message = "入职地址不能为空",groups = {IdeaTO.StaffEntry.class})
    private String entryAddress;
    /**
     * 是否入职
     */
    @NotNull(message = "是否入职不能为空",groups = {IdeaTO.StaffEntry.class})
    private Boolean whetherEntry;

    /**
     * 未入职原因
     */
    @NotNull(message = "未入职原因不能为空",groups = {IdeaTO.StaffEntry.class})
    private String denyEntryReason;
    /**
     * 备注
     */
    private String comment;

    public Boolean getWhetherAcceptAdmit() {
        return whetherAcceptAdmit;
    }

    public void setWhetherAcceptAdmit(Boolean whetherAcceptAdmit) {
        this.whetherAcceptAdmit = whetherAcceptAdmit;
    }

    public String getDenyAdmitReason() {
        return denyAdmitReason;
    }

    public void setDenyAdmitReason(String denyAdmitReason) {
        this.denyAdmitReason = denyAdmitReason;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Boolean getWhetherAccommodation() {
        return whetherAccommodation;
    }

    public void setWhetherAccommodation(Boolean whetherAccommodation) {
        this.whetherAccommodation = whetherAccommodation;
    }

    public Boolean getWhetherUseFirmPC() {
        return whetherUseFirmPC;
    }

    public void setWhetherUseFirmPC(Boolean whetherUseFirmPC) {
        this.whetherUseFirmPC = whetherUseFirmPC;
    }

    public String getEntryAddress() {
        return entryAddress;
    }

    public void setEntryAddress(String entryAddress) {
        this.entryAddress = entryAddress;
    }

    public Boolean getWhetherEntry() {
        return whetherEntry;
    }

    public void setWhetherEntry(Boolean whetherEntry) {
        this.whetherEntry = whetherEntry;
    }

    public String getDenyEntryReason() {
        return denyEntryReason;
    }

    public void setDenyEntryReason(String denyEntryReason) {
        this.denyEntryReason = denyEntryReason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFirstTestAdvice() {
        return firstTestAdvice;
    }

    public void setFirstTestAdvice(String firstTestAdvice) {
        this.firstTestAdvice = firstTestAdvice;
    }


    public Boolean getWhetherFirstTestPass() {
        return whetherFirstTestPass;
    }

    public void setWhetherFirstTestPass(Boolean whetherFirstTestPass) {
        this.whetherFirstTestPass = whetherFirstTestPass;
    }


    public String getSecondTestAdvice() {
        return secondTestAdvice;
    }

    public void setSecondTestAdvice(String secondTestAdvice) {
        this.secondTestAdvice = secondTestAdvice;
    }

    public Boolean getWhetherSecondTestPass() {
        return whetherSecondTestPass;
    }

    public void setWhetherSecondTestPass(Boolean whetherSecondTestPass) {
        this.whetherSecondTestPass = whetherSecondTestPass;
    }

    public String getSalaryFaceTime() {
        return salaryFaceTime;
    }

    public void setSalaryFaceTime(String salaryFaceTime) {
        this.salaryFaceTime = salaryFaceTime;
    }

    public String getSalaryFacePrincipal() {
        return salaryFacePrincipal;
    }

    public void setSalaryFacePrincipal(String salaryFacePrincipal) {
        this.salaryFacePrincipal = salaryFacePrincipal;
    }

    public String getFaceAdvice() {
        return faceAdvice;
    }

    public void setFaceAdvice(String faceAdvice) {
        this.faceAdvice = faceAdvice;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getBossAdvice() {
        return bossAdvice;
    }

    public void setBossAdvice(String bossAdvice) {
        this.bossAdvice = bossAdvice;
    }

    public Boolean getAgreedEmployed() {
        return agreedEmployed;
    }

    public void setAgreedEmployed(Boolean agreedEmployed) {
        this.agreedEmployed = agreedEmployed;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
}
