package com.bjike.goddess.negotiatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 协商前准备信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "negotiatemeeting_ready")
public class Ready extends BaseEntity {

    /**
     * 参会人
     */
    @Column(name = "attend",  columnDefinition = "VARCHAR(255)   COMMENT '参会人'")
    private String attend;

    /**
     * 需要协商的工作内容
     */
    @Column(name = "content",columnDefinition = "VARCHAR(255)   COMMENT '需要协商的工作内容'")
    private String content;

    /**
     * 需要协商的工作时长
     */
    @Column(name = "time",  columnDefinition = "VARCHAR(255)   COMMENT '需要协商的工作时长'")
    private String time;

    /**
     * 协商的工作内容最晚执行完毕期限
     */
    @Column(name = "timeLimit", columnDefinition = "VARCHAR(255)   COMMENT '协商的工作内容最晚执行完毕期限'")
    private String timeLimit;

    /**
     * 协商会议组织内容信息
     */
    @Column(name = "organization_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '协商会议组织内容信息'")
    private String organizationId;

    /**
     * 参会用户信息
     */
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '用户信息'")
    private String userId;

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}