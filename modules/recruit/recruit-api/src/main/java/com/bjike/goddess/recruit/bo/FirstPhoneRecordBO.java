package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.type.Gender;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 12:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FirstPhoneRecordBO extends BaseBO {
    /**
     * 日期
     */
    private String date;

    /**
     * 简历来源
     */
    private String resumeResource;

    /**
     * 岗位
     */
    private String position;
    /**
     * 应聘地区
     */
    private String area;
    /**
     * 应聘部门/项目组
     */
    private String projectGroup;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 联系方式
     */
    private String telephone;

    /**
     * 简历筛选是否通过
     */
    private Boolean whetherPass;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 通话是否成功
     */
    private Boolean whetherPhoneSuccess;

    /**
     * 未成功通话原因
     */
    private String phoneFailReason;

    /**
     * 是否有相关工作经验
     */
    private Boolean whetherWorkExperience;

    /**
     * 第一次电访了解到的情况
     */
    private String firstSituation;

    /**
     * 是否成功邀约初试
     */
    private Boolean whetherFirstInviteSuccess;

    /**
     * 未邀约成功原因
     */
    private String failureInviteReason;

    /**
     * 邀约初试时间
     */
    private String firstInterviewTime;

    /**
     * 初试负责人
     */
    private String firstInterviewPrincipal;

    /**
     * 初试地点
     */
    private String firstPlace;
    /**
     * 是否初试
     */
    private Boolean whetherFirstInterview;

    /**
     * 初试是否为面试
     */
    private Boolean whetherFaceTest;

    /**
     * 未应约初试原因
     */
    private String denyFirViewReason;


    /**
     * 是否需要复试
     */
    private Boolean retrial;
    /**
     * 复试时间
     */
    private String retrialTime;
    /**
     * 复试负责人
     */
    private String retrialOfficer;
    /**
     * 其他
     */
    private String other;

//    /**
//     * 附件地址
//     */
//    private String attachmentAddr;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public Boolean getRetrial() {
        return retrial;
    }

    public void setRetrial(Boolean retrial) {
        this.retrial = retrial;
    }

    public String getRetrialTime() {
        return retrialTime;
    }

    public void setRetrialTime(String retrialTime) {
        this.retrialTime = retrialTime;
    }

    public String getRetrialOfficer() {
        return retrialOfficer;
    }

    public void setRetrialOfficer(String retrialOfficer) {
        this.retrialOfficer = retrialOfficer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResumeResource() {
        return resumeResource;
    }

    public void setResumeResource(String resumeResource) {
        this.resumeResource = resumeResource;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getWhetherPass() {
        return whetherPass;
    }

    public void setWhetherPass(Boolean whetherPass) {
        this.whetherPass = whetherPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getWhetherPhoneSuccess() {
        return whetherPhoneSuccess;
    }

    public void setWhetherPhoneSuccess(Boolean whetherPhoneSuccess) {
        this.whetherPhoneSuccess = whetherPhoneSuccess;
    }

    public String getPhoneFailReason() {
        return phoneFailReason;
    }

    public void setPhoneFailReason(String phoneFailReason) {
        this.phoneFailReason = phoneFailReason;
    }

    public Boolean getWhetherWorkExperience() {
        return whetherWorkExperience;
    }

    public void setWhetherWorkExperience(Boolean whetherWorkExperience) {
        this.whetherWorkExperience = whetherWorkExperience;
    }

    public String getFirstSituation() {
        return firstSituation;
    }

    public void setFirstSituation(String firstSituation) {
        this.firstSituation = firstSituation;
    }

    public Boolean getWhetherFirstInviteSuccess() {
        return whetherFirstInviteSuccess;
    }

    public void setWhetherFirstInviteSuccess(Boolean whetherFirstInviteSuccess) {
        this.whetherFirstInviteSuccess = whetherFirstInviteSuccess;
    }

    public String getFailureInviteReason() {
        return failureInviteReason;
    }

    public void setFailureInviteReason(String failureInviteReason) {
        this.failureInviteReason = failureInviteReason;
    }

    public String getFirstInterviewTime() {
        return firstInterviewTime;
    }

    public void setFirstInterviewTime(String firstInterviewTime) {
        this.firstInterviewTime = firstInterviewTime;
    }

    public String getFirstInterviewPrincipal() {
        return firstInterviewPrincipal;
    }

    public void setFirstInterviewPrincipal(String firstInterviewPrincipal) {
        this.firstInterviewPrincipal = firstInterviewPrincipal;
    }

    public Boolean getWhetherFirstInterview() {
        return whetherFirstInterview;
    }

    public void setWhetherFirstInterview(Boolean whetherFirstInterview) {
        this.whetherFirstInterview = whetherFirstInterview;
    }

    public Boolean getWhetherFaceTest() {
        return whetherFaceTest;
    }

    public void setWhetherFaceTest(Boolean whetherFaceTest) {
        this.whetherFaceTest = whetherFaceTest;
    }

    public String getDenyFirViewReason() {
        return denyFirViewReason;
    }

    public void setDenyFirViewReason(String denyFirViewReason) {
        this.denyFirViewReason = denyFirViewReason;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

//    public String getAttachmentAddr() {
//        return attachmentAddr;
//    }
//
//    public void setAttachmentAddr(String attachmentAddr) {
//        this.attachmentAddr = attachmentAddr;
//    }
}
