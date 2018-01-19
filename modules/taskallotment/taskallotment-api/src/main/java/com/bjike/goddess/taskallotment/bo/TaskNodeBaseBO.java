package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 计划任务节点集合业务传输对象
 *
 * @Author: [ chenyang ]
 * @Date: [ 2018-01-18 11:58 ]
 * @Description: [ 计划任务节点集合业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskNodeBaseBO extends BaseBO {

    /**
     * tasknode
     */
    private List<TaskNodeBO> taskNodeList;


    public List<TaskNodeBO> getTaskNodeList() {
        return taskNodeList;
    }

    public void setTaskNodeList(List<TaskNodeBO> taskNodeList) {
        this.taskNodeList = taskNodeList;
    }
}