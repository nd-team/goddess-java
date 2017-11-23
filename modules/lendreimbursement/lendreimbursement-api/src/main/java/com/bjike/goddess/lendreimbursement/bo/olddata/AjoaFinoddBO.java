package com.bjike.goddess.lendreimbursement.bo.olddata;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 老系统的报销单号业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 10:39 ]
 * @Description: [ 老系统的报销单号业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AjoaFinoddBO extends BaseBO {

    /**
     * 报销单号
     */
    private String runNum;

    /**
     * 状态
     */
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