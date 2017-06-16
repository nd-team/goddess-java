package com.bjike.goddess.workjoin.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 工作交接时间规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workjoin_workjointimespecification")
public class WorkJoinTimeSpecification extends BaseEntity {

    /**
     * 工作交接原因
     */
    @Column(name = "workJoinCause", columnDefinition = "VARCHAR(255)   COMMENT '工作交接原因'")
    private String workJoinCause;

    /**
     * 交接流程
     */
    @Column(name = "joinProcess", columnDefinition = "VARCHAR(255)   COMMENT '交接流程'")
    private String joinProcess;

    /**
     * 对象
     */
    @Column(name = "object", columnDefinition = "VARCHAR(255)   COMMENT '对象'")
    private String object;

    /**
     * 交接时间
     */
    @Column(name = "joinTime", columnDefinition = "VARCHAR(255)   COMMENT '交接时间'")
    private String joinTime;


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