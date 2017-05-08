package com.bjike.goddess.workjoin.vo;

/**
 * 工作交接时间规范表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkJoinTimeSpecificationVO {

    /**
     * id
     */
    private String id;
    /**
     * 工作交接原因
     */
    private String workJoinCause;

    /**
     * 交接流程
     */
    private String joinProcess;

    /**
     * 对象
     */
    private String object;

    /**
     * 交接时间(例如1h)
     */
    private String joinTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkJoinCause() {
        return workJoinCause;
    }

    public void setWorkJoinCause(String workJoinCause) {
        this.workJoinCause = workJoinCause;
    }

    public String getJoinProcess() {
        return joinProcess;
    }

    public void setJoinProcess(String joinProcess) {
        this.joinProcess = joinProcess;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
}