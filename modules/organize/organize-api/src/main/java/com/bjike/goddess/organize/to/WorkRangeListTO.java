package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkRangeListTO extends BaseTO {

    /**
     * 工作范围
     */
    @NotBlank(message = "工作范围不能为空", groups = {ADD.class, EDIT.class})
    private String workRange;

    /**
     * 工作界面(节点)
     */
    private String node;

    /**
     * 使用状态
     */
    private Status status;

    /**
     * 创建时间
     */
    private String createTime;

    public String getWorkRange() {
        return workRange;
    }

    public void setWorkRange(String workRange) {
        this.workRange = workRange;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
