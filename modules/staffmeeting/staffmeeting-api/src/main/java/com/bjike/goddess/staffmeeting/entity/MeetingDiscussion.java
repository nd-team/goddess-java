package com.bjike.goddess.staffmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 会议讨论
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 会议讨论 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffmeeting_discussion")
public class MeetingDiscussion extends BaseEntity {

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
     * 发言内容
     */
    @Column(name = "discussion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言内容'")
    private String discussion;

    /**
     * 是否修改发言内容
     */
    @Column(name = "is_dissentContent", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否修改发言内容'")
    private Boolean dissentContent;

    /**
     * 修改后的发言内容
     */
    @Column(name = "modifyContent", columnDefinition = "VARCHAR(255)   COMMENT '修改后的发言内容'")
    private String modifyContent;

    /**
     * 纪要Id
     */
    @Column(name = "summaryId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '纪要Id'")
    private String summaryId;


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

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public Boolean getDissentContent() {
        return dissentContent;
    }

    public void setDissentContent(Boolean dissentContent) {
        this.dissentContent = dissentContent;
    }

    public String getModifyContent() {
        return modifyContent;
    }

    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}