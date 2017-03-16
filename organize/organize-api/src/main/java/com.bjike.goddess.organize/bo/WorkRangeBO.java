package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 工作范围信息设置传输对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
public class WorkRangeBO extends BaseBO {

    /**
     * 方向
     */
    private String direction;

    /**
     * 科目
     */
    private String project;

    /**
     * 专业分类
     */
    private String classify;

    /**
     * 工作范围
     */
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

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
