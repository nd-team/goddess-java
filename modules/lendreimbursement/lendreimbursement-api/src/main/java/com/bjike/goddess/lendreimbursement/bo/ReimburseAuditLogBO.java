package com.bjike.goddess.lendreimbursement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 报销审核日志表业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销审核日志表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseAuditLogBO extends BaseBO {

    /**
     * 审核人
     */
    private String userName;

    /**
     * 审核人编号
     */
    private String empNum;

    /**
     * 审核人职位
     */
    private String position;

    /**
     * 审核意见
     */
    private String content;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 报销记录Id
     */
    private String reimrecordId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getReimrecordId() {
        return reimrecordId;
    }

    public void setReimrecordId(String reimrecordId) {
        this.reimrecordId = reimrecordId;
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