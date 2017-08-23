package com.bjike.goddess.negotiatemeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 协商前准备信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReadyTO extends BaseTO {

    /**
     * 需要协商的工作内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "需要协商的工作内容不能为空")
    private String content;

    /**
     * 需要协商的工作时长
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "需要协商的工作时长不能为空")
    private String time;

    /**
     * 协商的工作内容最晚执行完毕期限
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "协商的工作内容最晚执行完毕期限不能为空")
    private String timeLimit;

    /**
     * 协商会议组织内容信息
     */
    private String organizationId;

    /**
     * 参会用户信息
     */
    private String userId;

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
}