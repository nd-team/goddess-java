package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.enums.TaskStatus;

import java.util.List;

/**
 * 项目表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableVO {

    /**
     * id
     */
    private String id;
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
     * 任务状态
     */
    private TaskStatus taskStatus;

    /**
     * 任务节点名称集合
     */
    private List<NodeVO> nodeS;

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public List<NodeVO> getNodeS() {
        return nodeS;
    }

    public void setNodeS(List<NodeVO> nodeS) {
        this.nodeS = nodeS;
    }
}