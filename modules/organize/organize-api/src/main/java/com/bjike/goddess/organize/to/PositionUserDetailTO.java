package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.enums.AgentType;
import com.bjike.goddess.organize.enums.WorkStatus;

/**
 * Created by ike on 17-9-6.
 */
public class PositionUserDetailTO extends BaseTO {
    /**
     * 轮岗层级
     */
    private String rotationLevel;
    /**
     * 担任状态
     */
    private WorkStatus workStatus;
    /**
     * 是否为代理岗位
     */
    private Boolean agent;
    /**
     * 代理类型
     */
    private AgentType agentType;
    /**
     * 职位
     */
    private String positionId;

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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
}
