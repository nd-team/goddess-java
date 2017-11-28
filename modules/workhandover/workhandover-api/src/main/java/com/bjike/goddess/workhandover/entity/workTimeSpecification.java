package com.bjike.goddess.workhandover.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.workhandover.enums.WorkHandoverReason;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 工作交接时间规范
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 工作交接时间规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workhandover_worktimespecification")
public class workTimeSpecification extends BaseEntity {

    /**
     * 工作交接原因
     */
    @Column(name = "workHandoverReason", columnDefinition = "VARCHAR(255)   COMMENT '工作交接原因'")
    private String workHandoverReason;

    /**
     * 交接流程
     */
    @Column(name = "workHandoverProcess", columnDefinition = "VARCHAR(255)   COMMENT '交接流程'")
    private String workHandoverProcess;

    /**
     * 对象
     */
    @Column(name = "objects", columnDefinition = "VARCHAR(255)   COMMENT '对象'")
    private String objects;

    /**
     * 交接时间
     */
    @Column(name = "handoverTime", columnDefinition = "DATE   COMMENT '交接时间'")
    private LocalDate handoverTime;

    public String getWorkHandoverProcess() {
        return workHandoverProcess;
    }

    public void setWorkHandoverProcess(String workHandoverProcess) {
        this.workHandoverProcess = workHandoverProcess;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    public LocalDate getHandoverTime() {
        return handoverTime;
    }

    public void setHandoverTime(LocalDate handoverTime) {
        this.handoverTime = handoverTime;
    }

    public String getWorkHandoverReason() {
        return workHandoverReason;
    }

    public void setWorkHandoverReason(String workHandoverReason) {
        this.workHandoverReason = workHandoverReason;
    }
}