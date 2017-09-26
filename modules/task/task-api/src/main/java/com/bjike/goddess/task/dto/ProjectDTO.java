package com.bjike.goddess.task.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.enums.ExecStatus;

/**
 * 项目数据传输
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:45]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectDTO extends BaseDTO {

    /**
     * 项目状态
     */
    private Status status;
    /**
     * 执行状态
     */
    private ExecStatus execStatus;



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

}
