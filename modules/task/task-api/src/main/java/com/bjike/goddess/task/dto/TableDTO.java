package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.enums.ExecStatus;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TableDTO extends BaseDTO {
    /**
     * 表状态
     */
    private Status status;
    /**
     * 执行状态
     */
    private ExecStatus execStatus;
    /**
     * 项目id
     */
    private String projectId;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
