package com.bjike.goddess.lendreimbursement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;

/**
 * 报销单号管理业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-12 09:19 ]
 * @Description: [ 报销单号管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinoddinforBO extends BaseBO {

    /**
     * 报销单号
     */
    private String runNum;

    /**
     * 单号开始数
     */
    private int startNum;

    /**
     * 单号结束数
     */
    private int endNum;

    /**
     * 报销单号状态
     */
    private Status status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getRunNum() {
        return runNum;
    }

    public void setRunNum(String runNum) {
        this.runNum = runNum;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}