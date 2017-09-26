package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.ExecStatus;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class RowTO extends BaseTO {
    /**
     * 所属表
     */
    @NotBlank(message = "所属表id不能为空",groups = {ADD.class})
    private String tableId;
    /**
     * 所属节点
     */
    @NotBlank(message = "所属节点不能为空",groups = {ADD.class})
    private String node;

    /**
     * 执行状态
     */
    private ExecStatus execStatus;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }
}
