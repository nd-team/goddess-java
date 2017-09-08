package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.organize.enums.AgentType;
import com.bjike.goddess.organize.enums.WorkStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织人员构成明细
 *
 * @Author: [chenjunhao]
 * @Date: [17-9-6 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_position_user_detail")
public class PositionUserDetail extends BaseEntity {
    /**
     * 用户id
     */
    @Column(nullable = false,columnDefinition = "VARCHAR(36) COMMENT '用户id'")
    private String userId;
    /**
     * 职位id
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) COMMENT '职位id'")
    private String positionId;
    /**
     * 轮岗层级
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '轮岗层级'")
    private String rotationLevel;
    /**
     * 担任状态
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '担任状态' ", nullable = false)
    private WorkStatus workStatus;
    /**
     * 是否为代理岗位
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '是否为代理岗位' ", nullable = false)
    private Boolean agent;
    /**
     * 代理类型
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '代理类型' ")
    private AgentType agentType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getRotationLevel() {
        return rotationLevel;
    }

    public void setRotationLevel(String rotationLevel) {
        this.rotationLevel = rotationLevel;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
    }

    public Boolean getAgent() {
        return agent;
    }

    public void setAgent(Boolean agent) {
        this.agent = agent;
    }

    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }
}
