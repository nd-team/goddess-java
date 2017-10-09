package com.bjike.goddess.taskallotment.vo;

/**
 * 任务节点名称
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-29 15:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NodeVO {
    /**
     * id
     */
    private String id;
    /**
     * 任务名称
     */
    private String taskName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
