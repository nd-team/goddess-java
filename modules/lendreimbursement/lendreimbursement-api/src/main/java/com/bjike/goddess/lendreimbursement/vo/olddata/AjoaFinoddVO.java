package com.bjike.goddess.lendreimbursement.vo.olddata;

/**
 * 老系统的报销单号表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 10:39 ]
 * @Description: [ 老系统的报销单号表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AjoaFinoddVO {

    /**
     * id
     */
    private String id;
    /**
     * 报销单号
     */
    private String runNum;

    /**
     * 状态
     */
    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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