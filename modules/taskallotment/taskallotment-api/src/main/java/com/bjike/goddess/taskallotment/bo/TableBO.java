package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.enums.TaskStatus;

import java.util.List;

/**
 * 项目表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableBO extends BaseBO {

    /**
     * 表名称
     */
    private String name;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 状态
     */
    private Status status;

    /**
     * 任务节点名称集合
     */
    private List<NodeBO> nodeS;
    /**
     * 任务状态
     */
    private TaskStatus taskStatus;

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public List<NodeBO> getNodeS() {
        return nodeS;
    }

    public void setNodeS(List<NodeBO> nodeS) {
        this.nodeS = nodeS;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "TableBO{" +
                "name='" + name + '\'' +
                ", creater='" + creater + '\'' +
                ", status=" + status +
                ", nodeS=" + nodeS +
                ", taskStatus=" + taskStatus +
                '}';
    }
}