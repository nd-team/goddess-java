package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;


/**
 * 报销单号管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-12 09:19 ]
 * @Description: [ 报销单号管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_finoddinfor")
public class Finoddinfor extends BaseEntity {

    /**
     * 报销单号
     */
    @Column(name = "runNum", nullable = false, unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '报销单号'")
    private String runNum;

    /**
     * 报销单号状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "INT(2)   COMMENT '报销单号状态'")
    private Status status;


    public String getRunNum() {
        return runNum;
    }

    public void setRunNum(String runNum) {
        this.runNum = runNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Finoddinfor() {
    }
    public Finoddinfor(String runNum) {
        super();
        this.runNum = runNum;
    }
}