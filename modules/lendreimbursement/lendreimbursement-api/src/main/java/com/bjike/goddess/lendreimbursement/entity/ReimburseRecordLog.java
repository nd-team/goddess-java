package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 报销记录日志表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销记录日志表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_reimburserecordlog")
public class ReimburseRecordLog extends BaseEntity {

    /**
     * 修改人
     */
    @Column(name = "userName",  columnDefinition = "VARCHAR(255)   COMMENT '修改人'")
    private String userName;

    /**
     * 修改人编号
     */
    @Column(name = "empNum",  columnDefinition = "VARCHAR(255)   COMMENT '修改人编号'")
    private String empNum;

    /**
     * 修改人职位
     */
    @Column(name = "position",  columnDefinition = "VARCHAR(255)   COMMENT '修改人职位'")
    private String position;

    /**
     * 修改内容
     */
    @Column(name = "content",  columnDefinition = "VARCHAR(255)   COMMENT '修改内容'")
    private String content;

    /**
     * 报销记录Id
     */
    @Column(name = "reimrecordId",  columnDefinition = "VARCHAR(255)   COMMENT '报销记录Id'")
    private String reimrecordId;




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

    public String getReimrecordId() {
        return reimrecordId;
    }

    public void setReimrecordId(String reimrecordId) {
        this.reimrecordId = reimrecordId;
    }



}