package com.bjike.goddess.lendreimbursement.entity.olddata;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 老系统的报销单号
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 10:39 ]
 * @Description: [ 老系统的报销单号 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "aj_oa_finance_finoddinfor")
public class AjoaFinodd extends BaseEntity {

    /**
     * 报销单号
     */
    @Column(name = "runNum",  columnDefinition = "VARCHAR(255)   COMMENT '报销单号'")
    private String runNum;

    /**
     * 状态
     */
    @Column(name = "status",  columnDefinition = "INT(2)   COMMENT '状态'")
    private int status;


    public String getRunNum() {
        return runNum;
    }

    public void setRunNum(String runNum) {
        this.runNum = runNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}