package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 会议讨论意见
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_discussion")
public class MeetingDiscussion extends BaseEntity {

    /**
     * 一轮意见
     */
    @Column(name = "firstDis", nullable = false, columnDefinition = "TEXT   COMMENT '一轮意见'")
    private String firstDis;

    /**
     * 二轮意见
     */
    @Column(name = "secondDis", columnDefinition = "TEXT  COMMENT '二轮意见'")
    private String secondDis;

    /**
     * 最终意见
     */
    @Column(name = "finalDis", columnDefinition = "TEXT   COMMENT '最终意见'")
    private String finalDis;

    /**
     * 发言人
     */
    @Column(name = "user", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言人'")
    private String user;

    /**
     * 发言人员工编号
     */
    @Column(name = "userNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言人员工编号'")
    private String userNum;

    /**
     * 纪要Id
     */
    @Column(name = "summaryId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '纪要Id'")
    private String summaryId;


    public String getFirstDis() {
        return firstDis;
    }

    public void setFirstDis(String firstDis) {
        this.firstDis = firstDis;
    }

    public String getSecondDis() {
        return secondDis;
    }

    public void setSecondDis(String secondDis) {
        this.secondDis = secondDis;
    }

    public String getFinalDis() {
        return finalDis;
    }

    public void setFinalDis(String finalDis) {
        this.finalDis = finalDis;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}