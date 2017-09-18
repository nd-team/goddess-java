package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.ExecStatus;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 表
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TableTO extends BaseTO {
    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空",groups = {ADD.class, EDIT.class})
    private String name;
    /**
     * 所属项目
     */
    @NotBlank(message = "所属项目不能为空",groups = {ADD.class, EDIT.class})
    private String projectId;

    /**
     * 执行状态
     */
    private ExecStatus execStatus;

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
