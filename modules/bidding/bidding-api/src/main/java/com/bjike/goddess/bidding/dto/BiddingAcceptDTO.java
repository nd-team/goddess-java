package com.bjike.goddess.bidding.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 招标问题受理和处理数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAcceptDTO extends BaseDTO {
    /**
     * 问题提出人
     */
    private String problemExhibitor;
    /**
     * 是否通报
     */
    private Boolean notification;
    /**
     * 状态
     */
    private String status;

    /**
     * 是否闭环
     */
    private Boolean closedLoop;

    public String getProblemExhibitor() {
        return problemExhibitor;
    }

    public void setProblemExhibitor(String problemExhibitor) {
        this.problemExhibitor = problemExhibitor;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
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