package com.bjike.goddess.task.vo;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.ExecStatus;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class RowVO implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 所属表
     */
    private String tableId;

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

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }
}
