package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.ADD;
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
public class TableBO extends BaseBO {
    /**
     * 表名称
     */
    private String name;

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

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }
}
