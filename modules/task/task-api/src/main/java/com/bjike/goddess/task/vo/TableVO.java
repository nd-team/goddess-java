package com.bjike.goddess.task.vo;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.ExecStatus;

import java.io.Serializable;

/**
 * 表
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TableVO implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 表名称
     */
    private String name;
    /**
     * 所属项目
     */
    private String projectId;

    /**
     * 执行状态
     */
    private ExecStatus execStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }
}
