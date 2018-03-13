package com.bjike.goddess.businessproject.to;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.TaskContract;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 合同实施过程信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractImplTO extends BaseTO {



    /**
     * 实际开工日期
     */
    @NotNull(message = "实际开工日期",groups = {EDIT.class})
    private String realityStartDate;

    /**
     * 实际完工日期
     */
    @NotNull(message = "实际完工日期",groups = {EDIT.class})
    private String realityCompleteTime;

    /**
     * 预计完工时间
     */
    @NotNull(message = "预计完工时间",groups = {EDIT.class})
    private String expectCompleteTime;

    /**
     * 进场状态
     */
    @NotNull(message = "进场状态",groups = {EDIT.class})
    private String approachStatus;

    /**
     * 工作状态
     */
    @NotNull(message = "工作状态",groups = {EDIT.class})
    private String workStatus;

    /**
     * 到货
     */
    @NotNull(message = "到货",groups = {EDIT.class})
    private String goods;

    /**
     * 初验
     */
    @NotNull(message = "初验",groups = {EDIT.class})
    private String initialTest;

    /**
     * 终验
     */
    @NotNull(message = "终验",groups = {EDIT.class})
    private String finalTest;

    /**
     * 商务评估（项目名称）
     */
    @NotNull(message = "商务评估（项目名称）",groups = {EDIT.class})
    private String businessAssessProject;

    /**
     * 商务评估（当前情况）
     */
    @NotNull(message = "商务评估（当前情况）",groups = {EDIT.class})
    private String businessAssessCase;

    /**
     * 商务回复反馈
     */
    @NotNull(message = "商务回复反馈",groups = {EDIT.class})
    private String businessReplyFeedback;

    /**
     * 项目负责人
     */
    @NotNull(message = "项目负责人",groups = {EDIT.class})
    private String projectCharge;

    public String getRealityStartDate() {
        return realityStartDate;
    }

    public void setRealityStartDate(String realityStartDate) {
        this.realityStartDate = realityStartDate;
    }

    public String getRealityCompleteTime() {
        return realityCompleteTime;
    }

    public void setRealityCompleteTime(String realityCompleteTime) {
        this.realityCompleteTime = realityCompleteTime;
    }

    public String getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(String expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public String getApproachStatus() {
        return approachStatus;
    }

    public void setApproachStatus(String approachStatus) {
        this.approachStatus = approachStatus;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getInitialTest() {
        return initialTest;
    }

    public void setInitialTest(String initialTest) {
        this.initialTest = initialTest;
    }

    public String getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(String finalTest) {
        this.finalTest = finalTest;
    }

    public String getBusinessAssessProject() {
        return businessAssessProject;
    }

    public void setBusinessAssessProject(String businessAssessProject) {
        this.businessAssessProject = businessAssessProject;
    }

    public String getBusinessAssessCase() {
        return businessAssessCase;
    }

    public void setBusinessAssessCase(String businessAssessCase) {
        this.businessAssessCase = businessAssessCase;
    }

    public String getBusinessReplyFeedback() {
        return businessReplyFeedback;
    }

    public void setBusinessReplyFeedback(String businessReplyFeedback) {
        this.businessReplyFeedback = businessReplyFeedback;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }
}