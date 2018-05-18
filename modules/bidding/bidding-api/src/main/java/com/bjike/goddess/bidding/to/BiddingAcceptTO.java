package com.bjike.goddess.bidding.to;

import com.bjike.goddess.bidding.entity.BiddingAccept;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 招标问题受理和处理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAcceptTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    public interface TestNotification{}


    /**
     * 受理问题编号(对外)
     */
    private String problemNum;


    /**
     * 问题提出人
     */
    @NotBlank(message = "问题提出人不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String problemExhibitor;

    /**
     * 问题类型
     */
    @NotBlank(message = "问题类型不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String problemType;

    /**
     * 问题发现形式
     */
    @NotBlank(message = "问题发现形式不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String problemDiscoveryForm;

    /**
     * 时间(背景)描述
     */
    @NotBlank(message = "时间(背景)描述不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String eventDescription;

    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String problemDescription;

    /**
     * 是否通报
     */
    @NotNull(message = "是否通报不能为空",groups = BiddingAcceptTO.TestNotification.class)
    private Boolean notification;

    /**
     * 获取时间(问题提出时间)
     */
    @NotBlank(message = "获取时间(问题提出时间)不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String getTime;

    /**
     * 期望处理时间
     */
    @NotBlank(message = "期望处理时间不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String expectDealTime;

    /**
     * 协助部门
     */
    private String assistDept;

    /**
     * 处理协商时间
     */
    @NotBlank(message = "处理协商时间不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String processTime;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空",groups = BiddingAcceptTO.TestAdd.class)
    private String status;

    /**
     * 是否闭环
     */
    @NotNull(message = "是否闭环不能为空",groups = BiddingAcceptTO.TestEdit.class)
    private Boolean closedLoop;



    public String getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(String problemNum) {
        this.problemNum = problemNum;
    }


    public String getProblemExhibitor() {
        return problemExhibitor;
    }

    public void setProblemExhibitor(String problemExhibitor) {
        this.problemExhibitor = problemExhibitor;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDiscoveryForm() {
        return problemDiscoveryForm;
    }

    public void setProblemDiscoveryForm(String problemDiscoveryForm) {
        this.problemDiscoveryForm = problemDiscoveryForm;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getExpectDealTime() {
        return expectDealTime;
    }

    public void setExpectDealTime(String expectDealTime) {
        this.expectDealTime = expectDealTime;
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }
}