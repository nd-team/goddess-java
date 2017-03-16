package com.bjike.goddess.organize.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 岗位层级展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:03]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public class ArrangementVO {


    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 层级
     */
    private String arrangement;

    /**
     * 描述
     */
    private String description;

    /**
     * 使用状态
     */
    private Status status;

    /**
     * 创建时间
     */
    private String createTime;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
